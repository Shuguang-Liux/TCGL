package com.example.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
/**
 * @author Shuguang_Liux
 * @package com.example.demo.service.impl
 * @Description ToDo
 * @Date 2020/7/16 9:20
 *a
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     * @param userEntity
     * @return
     */
    @Override
    public Result<String> getInfo(UserEntity userEntity){
        Result<String> mapResult = new Result<>();
        if (StringUtils.isNotBlank(userEntity.getUserName()) && StringUtils.isNotBlank(userEntity.getUserPassword())){
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name",userEntity.getUserName());
            queryWrapper.eq("user_password",userEntity.getUserPassword());
            UserEntity userEntityRes = userDao.selectOne(queryWrapper);
            if (null != userEntityRes){
                mapResult.setCode(200);
            }else {
                mapResult.setError(400,"用户不存在，不允许登录！");
            }
        }else {
            mapResult.setError(400,"用户名密码不能为空！");
        }
        return mapResult;
    }

    /**
     * 更新用户信息
     * @param userEntity
     * @return
     */
    @Override
    public Result<String> updateUserInfo(UserEntity userEntity){
        Result<String> result = new Result<>();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        int count = userDao.update(userEntity,queryWrapper);
        if (count == 1){
            result.setMessage("更新成功");
            return result;
        }
        return result;
    }

    /**
     * 跟新用户删除状态
     * @param userEntity
     * @return
     */
    @Override
    public Result<String> deleteUserInfo(UserEntity userEntity) {
        Result<String> result = new Result<>();
        userEntity.setDeleteState("Y");
        int count = userDao.updateById(userEntity);
        if (count == 1){
            result.setCode(200);
        }else {
            result.setError(400,"删除失败");
        }
        return result;
     }

    /**
     * 管理员用户插入普通用户信息
     * @param userEntity
     * @return
     */
    @Override
    public Result<String> insertUserInfo(UserEntity userEntity) {
        Result<String> result = new Result<>();
        //非空判断
        if (StringUtils.isBlank(userEntity.getUserName()) || StringUtils.isBlank(userEntity.getUserPassword())) {
            result.setError(400,"用户信息不能为空");
            return result;
        }
        //查询用户是否存在
        if (userService.countUser(userEntity.getUserName(),1)){
            result.setError(400,"用户已存在！");
        }else {
            int count = userDao.insert(userEntity);
            if (count == 1){
                result.setMessage("插入成功");
            }else {
                result.setError(500,"插入失败");
            }
        }
        return result;
    }


    /**
     * 根据用户名的角色判断用户是否存在
     * @param userName
     * @param userRole
     * @return
     */
    @Override
    public boolean countUser(String userName, int userRole) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        queryWrapper.eq("user_role",userRole);
        int count = userDao.selectCount(queryWrapper);
        return count > 0;
    }

}
