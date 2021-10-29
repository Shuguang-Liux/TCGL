package com.tcgl.service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tcgl.common.util.PageSupport;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.service.dao.UserDao;
import com.tcgl.serviceapi.api.UserApi;
import com.tcgl.serviceapi.entity.UserEntity;
import org.apache.dubbo.config.annotation.DubboService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户api impl
 *
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description 管理员处理用户实现
 * @Date 2020/9/9 17:02
 **/
@DubboService
public class UserApiImpl implements UserApi {

    private final static Logger logger = LoggerFactory.getLogger(UserApiImpl.class);

    @Autowired
    private UserDao userDao;

    /**
     * 检查用户数量
     *
     * @param userEntity 用户实体
     * @return {@link Boolean}
     */
    @Override
    public Boolean checkUserCount(UserEntity userEntity) {
            LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(UserEntity::getUsername,userEntity.getUsername())
                    .eq(UserEntity::getPassword,userEntity.getPassword());
            Integer count = userDao.selectCount(lambdaQueryWrapper);
            return count > 0;
    }

    /**
     * 根据用户名查询
     *
     * @param userName 用户名
     * @return {@link UserEntity}
     */
    @Override
    public UserEntity getUserInfoByName(String userName) {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("username",userName);
        return userDao.selectOne(userEntityQueryWrapper);
    }

    /**
     * 根据用户名更新用户密码
     *
     * @param userEntity 用户实体
     * @return {@link ResultVo}<{@link Boolean}>
     */
    @Override
    public ResultVo<Boolean> updateAccountInfo(UserEntity userEntity) {
        try {
            userDao.updateById(userEntity);
        } catch (Exception e) {
            logger.error("更新失败"+e.getMessage());
            return ResultVo.fail(400,"更新失败，请联系管理员");
        }
        return ResultVo.ok();
    }

    /**
     * 注册
     *
     * @param userEntity 用户实体
     * @return {@link ResultVo}<{@link ?}>
     */
    @Override
    public ResultVo<?> register(UserEntity userEntity) {
        try {
            userDao.insert(userEntity);
        } catch (Exception e) {
            logger.error("插入失败"+e.getMessage());
            return ResultVo.fail(400,"注册失败，请联系管理员");
        }
        return ResultVo.ok();
    }

    /**
     * 删除
     *
     * @param ids id
     * @return {@link ResultVo}<{@link Boolean}>
     */
    @Override
    public ResultVo<Boolean> delete(String ids) {
        userDao.deleteBatchIds(Arrays.asList(ids.split(",")));
        return ResultVo.ok();
    }

    @Override
    public List<Map<String, String>> userList() {
        List<UserEntity> list = userDao.selectList(new UpdateWrapper<UserEntity>().eq("username","admin").set("delete_status","Y"));
        userDao.update(null,new QueryWrapper<UserEntity>().eq("username","admin"));
       List<Map<String,String>> mapList = list.stream().map(userEntity ->
       { Map<String,String> remap = new HashMap<>(2);
           remap.put("username",userEntity.getUsername());
           remap.put("password",userEntity.getPassword());
           return remap;
        }).collect(Collectors.toList());

       list.forEach(o -> {
           o.setDeleteStatus(0);
       });
       return null;
     }

    @Override
    public IPage<UserEntity> userPage(UserEntity userEntity) {
        IPage<UserEntity> page = PageSupport.getInstance().buildPage();
        QueryWrapper<UserEntity> queryWrapper = PageSupport.getInstance().buildSortWrapper();
        queryWrapper.eq("delete_status",userEntity.getDeleteStatus());
        return userDao.selectPage(page,queryWrapper);
    }

}
