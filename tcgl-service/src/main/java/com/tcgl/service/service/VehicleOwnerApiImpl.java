package com.tcgl.service.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.service.dao.VehicleOwnerDao;
import com.tcgl.serviceapi.api.AccessRecordApi;
import com.tcgl.serviceapi.api.PaymentRecordApi;
import com.tcgl.serviceapi.api.VehicleOwnerApi;
import com.tcgl.serviceapi.entity.AccessRecordEntity;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;


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

    /**
     * 插入所有者信息，规定同时输入月租金信息
     *
     * @param params
     * @return
     */
    @Override
    public ResultVo<?> insertVehicleOwner(JSONObject params) {
        if (Objects.nonNull(params.getString("licensePlate"))) {
            return ResultVo.fail("400","主信息不能为空");
        }
        VehicleOwnerEntity vehicleOwnerEntity = new VehicleOwnerEntity();
        vehicleOwnerEntity.setLicensePlate(params.getString("licensePlate"));
        if (Objects.nonNull(params.getString("vehicleOwner"))) {
            vehicleOwnerEntity.setVehicleOwner(params.getString("vehicleOwner"));
        }
        //暂定只能由管理员输入
        vehicleOwnerEntity.setCreatePerson("admin");
        //赋值插入时间为当前时间
        vehicleOwnerEntity.setCreateTime(new Date());
        vehicleOwnerDao.insert(vehicleOwnerEntity);
        //mybatis-plus插入后自动把id映射到实体，获取自增主键
        params.put("ownerId", vehicleOwnerEntity.getId());
        //插入租金用户信息
         ResultVo<?> resultVo = paymentRecordApi.insertPaymentInfo(params);
        return resultVo;
    }

    /**
     * 列表查询
     *
     * @param param
     * @return
     */
    @Override
    public ResultVo<IPage<VehicleOwnerEntity>> selectByPage(Map<String, String> param) {
        ResultVo<IPage<VehicleOwnerEntity>> resultVo = new ResultVo<>();
        QueryWrapper<VehicleOwnerEntity> queryWrapper = new QueryWrapper<>();
        //牌照赋值
        if (param.get("licensePlate") != null) {
            queryWrapper.eq("license_plate", param.get("licensePlate"));
        }
        //主键倒序
        queryWrapper.orderByDesc("id");
        //页面数量
        Page<VehicleOwnerEntity> page = new Page<>(Long.parseLong(String.valueOf(param.get("pageNum"))), Long.parseLong(String.valueOf(param.get("pageSize"))));
        IPage<VehicleOwnerEntity> vehicleOwnerIPage = vehicleOwnerDao.selectPage(page, queryWrapper);
        resultVo.setResult(vehicleOwnerIPage);
        return resultVo;
    }

    /**
     * 导出
     *
     * @param param
     * @return
     */
    @Override
    public ResultVo<Map<String, Object>> exportVehicleOwnerList(Map<String, Object> param) {
        ResultVo<Map<String, Object>> resultVo = new ResultVo<>();
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<VehicleOwnerEntity> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(param.get("licensePlate"))) {
            queryWrapper.eq("license_plate", param.get("licensePlate"));
        }
        if (Objects.nonNull(param.get("vehicleOwner"))) {
            queryWrapper.eq("vehicle_owner", param.get("vehicleOwner"));
        }
        if (Objects.nonNull(param.get("isValid"))) {
            queryWrapper.eq("is_valid", param.get("isValid"));
        }
        queryWrapper.orderByDesc("id");
        List<VehicleOwnerEntity> list = vehicleOwnerDao.selectList(queryWrapper);
        String[] titles = this.getHeadTitles();
        map.put("titles", titles);
        List<Object[]> dataList = new ArrayList<>();
        if (list != null) {
            Set<String> licensePlateSet = new HashSet<>();
            for (VehicleOwnerEntity vehicleOwnerEntity : list) {
                licensePlateSet.add(vehicleOwnerEntity.getLicensePlate());
            }
            //日期格式转化
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //避免重复请求数据库，造成数据库资源占用（结果一次性查出）
            ResultVo<Map<String, AccessRecordEntity>> accessRecordVo = accessRecordApi.getAccessRecordByLicensePlateSet(licensePlateSet);
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
                if (accessRecordVo.getSuccess() && accessRecordVo.getResult() != null &&
                        accessRecordVo.getResult().get(e.getLicensePlate()) != null) {
                    //入园时间
                    objects[7] = simpleDateFormat.format(accessRecordVo.getResult().get(e.getLicensePlate()).getEnterTime());
                    //出园时间(三木运算符进行区别赋值)
                    objects[8] = accessRecordVo.getResult().get(e.getLicensePlate()).getOutTime() == null ? null : simpleDateFormat.format(accessRecordVo.getResult().get(e.getLicensePlate()).getOutTime());
                    //入园时长统计
                    objects[9] = accessRecordVo.getResult().get(e.getLicensePlate()).getTimeCount() == null ? null : accessRecordVo.getResult().get(e.getLicensePlate()).getTimeCount();
                    //价格
                    objects[10] = accessRecordVo.getResult().get(e.getLicensePlate()).getBillingPrice() == null ? null : accessRecordVo.getResult().get(e.getLicensePlate()).getBillingPrice();
                    //是否出园
                    objects[11] = accessRecordVo.getResult().get(e.getLicensePlate()).getIsOut().equals("Y") ? "已出园" : "未出园";
                    //次数统计
                    objects[12] = accessRecordVo.getResult().get(e.getLicensePlate()).getAccessTimes();
                    //预付费用户
                    objects[13] = accessRecordVo.getResult().get(e.getLicensePlate()).getIsPrepayment().equals("Y") ? "有效" : "无效";
                }
                //添加到list中
                dataList.add(objects);
            });
        }
        map.put("dataList", dataList);
        resultVo.setResult(map);
        return resultVo;
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
        ResultVo<VehicleOwnerEntity> resultVo = new ResultVo<>();
        VehicleOwnerEntity vehicleOwnerEntityNew = new VehicleOwnerEntity();
        vehicleOwnerEntityNew = vehicleOwnerDao.getVehicleOwnerAndAccessRecordHistory(vehicleOwnerEntity);
        resultVo.setResult(vehicleOwnerEntityNew);
        return resultVo;
    }

    /**
     * 自测试导出
     *
     * @param param
     * @param headTitleLength
     * @return
     */
    @Override
    public Map<String, String[]> exportVehicleOwnerAndHistory(Map<String, Object> param, Integer headTitleLength) {
        Map<String, String[]> stringListMap = new HashMap<>();
        QueryWrapper<VehicleOwnerEntity> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(param.get("licensePlate"))) {
            queryWrapper.eq("license_plate", param.get("licensePlate"));
        }
        if (Objects.nonNull(param.get("vehicleOwner"))) {
            queryWrapper.eq("vehicle_owner", param.get("vehicleOwner"));
        }
        if (Objects.nonNull(param.get("isValid"))) {
            queryWrapper.eq("is_valid", param.get("isValid"));
        }
        queryWrapper.orderByDesc("id");
        List<VehicleOwnerEntity> list = vehicleOwnerDao.selectList(queryWrapper);
//        String[] titles = this.getHeadTitles();
        if (list != null) {
            Set<String> licensePlateSet = new HashSet<>();
            for (VehicleOwnerEntity vehicleOwnerEntity : list) {
                licensePlateSet.add(vehicleOwnerEntity.getLicensePlate());
            }
            //日期格式转化
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //避免重复请求数据库，造成数据库资源占用（结果一次性查出）
            ResultVo<Map<String, AccessRecordEntity>> accessRecordVo = accessRecordApi.getAccessRecordByLicensePlateSet(licensePlateSet);
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
                if (accessRecordVo.getSuccess() && accessRecordVo.getResult() != null &&
                        accessRecordVo.getResult().get(e.getLicensePlate()) != null) {
                    //入园时间
                    objects[7] = simpleDateFormat.format(accessRecordVo.getResult().get(e.getLicensePlate()).getEnterTime());
                    //出园时间(三木运算符进行区别赋值)
                    objects[8] = accessRecordVo.getResult().get(e.getLicensePlate()).getOutTime() == null ? null : simpleDateFormat.format(accessRecordVo.getResult().get(e.getLicensePlate()).getOutTime());
                    //入园时长统计
                    objects[9] = accessRecordVo.getResult().get(e.getLicensePlate()).getTimeCount() == null ? null : String.valueOf(accessRecordVo.getResult().get(e.getLicensePlate()).getTimeCount());
                    //价格
                    objects[10] = accessRecordVo.getResult().get(e.getLicensePlate()).getBillingPrice() == null ? null : String.valueOf(accessRecordVo.getResult().get(e.getLicensePlate()).getBillingPrice());
                    //是否出园
                    objects[11] = "Y".equals(accessRecordVo.getResult().get(e.getLicensePlate()).getIsOut()) ? "已出园" : "未出园";
                    //次数统计
                    objects[12] = String.valueOf(accessRecordVo.getResult().get(e.getLicensePlate()).getAccessTimes());
                    //预付费用户
                    objects[13] = "Y".equals(accessRecordVo.getResult().get(e.getLicensePlate()).getIsPrepayment()) ? "有效" : "无效";
                }
                //添加到list中
                stringListMap.put(e.getLicensePlate(), objects);
            });
        }
        return stringListMap;
    }


}
