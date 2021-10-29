package com.tcgl.service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tcgl.common.exception.BaseBusinessException;
import com.tcgl.common.util.PageSupport;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.service.dao.VehicleOwnerDao;
import com.tcgl.serviceapi.api.AccessRecordApi;
import com.tcgl.serviceapi.api.PaymentRecordApi;
import com.tcgl.serviceapi.api.VehicleOwnerApi;
import com.tcgl.serviceapi.entity.AccessRecordEntity;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 车主api impl
 *
 * @author sun
 * @date 2021/06/25
 */
@DubboService
public class VehicleOwnerApiImpl implements VehicleOwnerApi {
    @Autowired
    private VehicleOwnerDao vehicleOwnerDao;

    @Autowired
    private PaymentRecordApi paymentRecordApi;

    @Autowired
    private AccessRecordApi accessRecordApi;
    
    public final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 插入车主
     * 插入所有者信息，规定同时输入月租金信息
     *
     * @param vehicleOwnerEntity 车主实体
     * @return {@link ResultVo}<{@link ?}>
     */
    @Override
    public ResultVo<?> insertVehicleOwner(VehicleOwnerEntity vehicleOwnerEntity) {
        if (StringUtils.isEmpty(vehicleOwnerEntity.getLicensePlate())) {
            throw new BaseBusinessException("主信息不能为空");
        }
        //暂定只能由管理员输入
        vehicleOwnerEntity.setCreatePerson("admin");
        //赋值插入时间为当前时间
        vehicleOwnerEntity.setCreateTime(new Date());
        vehicleOwnerDao.insert(vehicleOwnerEntity);
        //插入租金用户信息
        paymentRecordApi.insertPaymentInfo(vehicleOwnerEntity);
        return ResultVo.ok();
    }

    /**
     * 选择的页面
     * 列表查询
     *
     * @param vehicleOwnerEntity 车主实体
     * @return {@link IPage}<{@link VehicleOwnerEntity}>
     */
    @Override
    public IPage<VehicleOwnerEntity> selectByPage(VehicleOwnerEntity vehicleOwnerEntity) {
        QueryWrapper<VehicleOwnerEntity> queryWrapper = PageSupport.getInstance().buildSortWrapper();
        //牌照赋值
        if (StringUtils.isNotEmpty(vehicleOwnerEntity.getLicensePlate())) {
            queryWrapper.eq("license_plate", vehicleOwnerEntity.getLicensePlate());
        }
        //主键倒序
        queryWrapper.orderByDesc("id");
        //页面数量
        IPage<VehicleOwnerEntity> page = PageSupport.getInstance().buildPage();
        return vehicleOwnerDao.selectPage(page, queryWrapper);
    }

    private String[] getHeadTitles() {
        return new String[]{
                "车辆牌照", "车辆所有人", "创建时间", "创建人", "更新时间", "更新人", "有效状态",
                "进园时间", "出园时间", "入园时长统计", "价格", "是否出园", "次数", "是否预付费用户"
        };
    }

    /**
     * 取VehicleOwner和AccessRecordHistory对应列表信息
     *
     * @param vehicleOwnerEntity
     * @return
     */
    @Override
    public ResultVo<VehicleOwnerEntity> getVehicleOwnerAndAccessRecordHistory(VehicleOwnerEntity vehicleOwnerEntity) {
        VehicleOwnerEntity vehicleOwnerEntityNew  = vehicleOwnerDao.getVehicleOwnerAndAccessRecordHistory(vehicleOwnerEntity);
        return ResultVo.ok(vehicleOwnerEntityNew);
    }

    /**
     * 出口车主和历史
     *
     * @param vehicleOwnerEntity 车主实体
     * @param headTitleLength    头标题长度
     * @return {@link Map}<{@link String}, {@link String[]}>
     */
    @Override
    public Map<String, String[]> exportVehicleOwnerAndHistory(VehicleOwnerEntity vehicleOwnerEntity, Integer headTitleLength) {
        Map<String, String[]> stringListMap = new HashMap<>();
        QueryWrapper<VehicleOwnerEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(vehicleOwnerEntity.getLicensePlate())) {
            queryWrapper.eq("license_plate", vehicleOwnerEntity.getLicensePlate());
        }
        if (StringUtils.isNotEmpty(vehicleOwnerEntity.getVehicleOwner())) {
            queryWrapper.eq("vehicle_owner", vehicleOwnerEntity.getVehicleOwner());
        }
        if (StringUtils.isNotEmpty(vehicleOwnerEntity.getIsValid())) {
            queryWrapper.eq("is_valid", vehicleOwnerEntity.getIsValid());
        }
        queryWrapper.orderByDesc("id");
        List<VehicleOwnerEntity> list = vehicleOwnerDao.selectList(queryWrapper);
//        String[] titles = this.getHeadTitles();
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> licensePlateSet  = list.stream().map(VehicleOwnerEntity::getLicensePlate).collect(Collectors.toSet());
            //日期格式转化
            
            //避免重复请求数据库，造成数据库资源占用（结果一次性查出）
            Map<String, AccessRecordEntity> accessRecordVo = accessRecordApi.getAccessRecordByLicensePlateSet(licensePlateSet);
            list.forEach(e -> {
                //初始化数组长度
                String[] objects = new String[headTitleLength];
                //车牌号
                objects[0] = e.getLicensePlate();
                //车辆所有人
                objects[1] = e.getVehicleOwner();
                //创建时间
                objects[2] = simpleDateFormat.format(e.getCreateTime());
                //创建人
                objects[3] = e.getCreatePerson();
                //更新时间
                objects[4] = e.getUpdateTime() == null ? null : simpleDateFormat.format(e.getUpdateTime());
                //更新人
                objects[5] = e.getUpdatePerson() == null ? null : e.getUpdatePerson();
                //是否有效
                objects[6] = "Y".equals(e.getIsValid()) ? "有效" : "无效";
                //获取入园记录表中对应信息
                if (accessRecordVo.get(e.getLicensePlate()) != null) {
                    //入园时间
                    objects[7] = simpleDateFormat.format(accessRecordVo.get(e.getLicensePlate()).getEnterTime());
                    //出园时间(三木运算符进行区别赋值)
                    objects[8] = accessRecordVo.get(e.getLicensePlate()).getOutTime() == null ? null : simpleDateFormat.format(accessRecordVo.get(e.getLicensePlate()).getOutTime());
                    //入园时长统计
                    objects[9] = accessRecordVo.get(e.getLicensePlate()).getTimeCount() == null ? null : String.valueOf(accessRecordVo.get(e.getLicensePlate()).getTimeCount());
                    //价格
                    objects[10] = accessRecordVo.get(e.getLicensePlate()).getBillingPrice() == null ? null : String.valueOf(accessRecordVo.get(e.getLicensePlate()).getBillingPrice());
                    //是否出园
                    objects[11] = "Y".equals(accessRecordVo.get(e.getLicensePlate()).getIsOut()) ? "已出园" : "未出园";
                    //次数统计
                    objects[12] = String.valueOf(accessRecordVo.get(e.getLicensePlate()).getAccessTimes());
                    //预付费用户
                    objects[13] = "Y".equals(accessRecordVo.get(e.getLicensePlate()).getIsPrepayment()) ? "有效" : "无效";
                }
                //添加到list中
                stringListMap.put(e.getLicensePlate(), objects);
            });
        }
        return stringListMap;
    }

    @Override
    public Map<String, Object> exportVehicleOwnerList(VehicleOwnerEntity vehicleOwnerEntity) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<VehicleOwnerEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(vehicleOwnerEntity.getLicensePlate())) {
            queryWrapper.eq("license_plate", vehicleOwnerEntity.getLicensePlate());
        }
        if (StringUtils.isNotEmpty(vehicleOwnerEntity.getVehicleOwner())) {
            queryWrapper.eq("vehicle_owner", vehicleOwnerEntity.getVehicleOwner());
        }
        if (StringUtils.isNotEmpty(vehicleOwnerEntity.getIsValid())) {
            queryWrapper.eq("is_valid", vehicleOwnerEntity.getIsValid());
        }
        queryWrapper.orderByDesc("id");
        List<VehicleOwnerEntity> list = vehicleOwnerDao.selectList(queryWrapper);
        String[] titles = this.getHeadTitles();
        map.put("titles", titles);
        List<Object[]> dataList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> licensePlateSet = list.stream().map(VehicleOwnerEntity::getLicensePlate).collect(Collectors.toSet());
            //日期格式转化
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //避免重复请求数据库，造成数据库资源占用（结果一次性查出）
            Map<String, AccessRecordEntity> accessRecordVo = accessRecordApi.getAccessRecordByLicensePlateSet(licensePlateSet);
            list.forEach(e -> {
                //初始化数组长度
                Object[] objects = new Object[titles.length];
                //车牌号
                objects[0] = e.getLicensePlate();
                //车辆所有人
                objects[1] = e.getVehicleOwner();
                //创建时间
                objects[2] = simpleDateFormat.format(e.getCreateTime());
                //创建人
                objects[3] = e.getCreatePerson();
                //更新时间
                objects[4] = e.getUpdateTime() == null ? null : simpleDateFormat.format(e.getUpdateTime());
                //更新人
                objects[5] = e.getUpdatePerson() == null ? null : e.getUpdatePerson();
                //是否有效
                objects[6] = e.getIsValid().equals("Y") ? "有效" : "无效";
                //获取入园记录表中对应信息
                if (Objects.nonNull(accessRecordVo) &&
                        accessRecordVo.get(e.getLicensePlate()) != null) {
                    //入园时间
                    objects[7] = simpleDateFormat.format(accessRecordVo.get(e.getLicensePlate()).getEnterTime());
                    //出园时间(三木运算符进行区别赋值)
                    objects[8] = accessRecordVo.get(e.getLicensePlate()).getOutTime() == null ? null : simpleDateFormat.format(accessRecordVo.get(e.getLicensePlate()).getOutTime());
                    //入园时长统计
                    objects[9] = accessRecordVo.get(e.getLicensePlate()).getTimeCount() == null ? null : accessRecordVo.get(e.getLicensePlate()).getTimeCount();
                    //价格
                    objects[10] = accessRecordVo.get(e.getLicensePlate()).getBillingPrice() == null ? null : accessRecordVo.get(e.getLicensePlate()).getBillingPrice();
                    //是否出园
                    objects[11] = accessRecordVo.get(e.getLicensePlate()).getIsOut().equals("Y") ? "已出园" : "未出园";
                    //次数统计
                    objects[12] = accessRecordVo.get(e.getLicensePlate()).getAccessTimes();
                    //预付费用户
                    objects[13] = accessRecordVo.get(e.getLicensePlate()).getIsPrepayment().equals("Y") ? "有效" : "无效";
                }
                //添加到list中
                dataList.add(objects);
            });
        }
        map.put("dataList", dataList);
        return map;
    }
}
