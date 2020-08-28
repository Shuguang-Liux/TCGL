package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserDao;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
     * @param user
     * @return
     */
    @Override
    public Result getInfo(User user){
        Result result = new Result();
        result.setMessage("信息有误");
        if (StringUtils.isNotBlank(user.getUserName()) && StringUtils.isNotBlank(user.getUserPassword())){
            User userNew = new User();
            userNew.setUserName(user.getUserName());
            userNew.setUserPassword(user.getUserPassword());
            User userRes = userDao.getInfo(userNew);
            if (null != userRes){
                System.out.println("存在此用户，正常登录操作");
                result.setMessage("存在此用户，正常登录操作");
                result.setCode(200);
            }else {
                result.setMessage("账户密码不正确");
            }
            return result;
        }
        return result;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public Result updateUserInfo(User user){
        Result result = new Result();
        result.setMessage("更新失败");
        int count = userDao.updateUserInfo(user);
        if (count == 1){
            result.setMessage("更新成功");
            return result;
        }
        return result;
    }

    /**
     * 跟新用户删除状态
     * @param user
     * @return
     */
    @Override
    public Result deleteUserInfo(User user) {
        Result result = new Result();
        result.setMessage("用户名不能为空");
        if (StringUtils.isNotBlank(user.getUserName())){
            User userNew = new User();
            userNew.setUserName(user.getUserName());
            List<User> list = userDao.getAllUserByUsername(user);
            if (CollectionUtils.isEmpty(list)){
                result.setMessage("用户不存在");
            }else {
                for (User user1 : list) {
                    user1.setDeleteState("Y");
                    userDao.updateByPrimaryKey(user1);
                }
                result.setMessage("更新成功");
            }
            return result;
        }
        return result;

    }

    /**
     * 管理员用户插入普通用户信息
     * @param user
     * @return
     */
    @Override
    public Result insertUserInfo(User user) {
        Result result = new Result();
        //非空判断
        if (StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getUserPassword())) {
            result.setCode(400);
            result.setMessage("输入信息不能为空！");
            return result;
        }
        //查询用户是否存在
        if (userService.countUser(user.getUserName(),1)){
            result.setCode(400);
            result.setMessage("用户已存在！");
        }else {
            int count = userDao.insertUserInfo(user);
            if (count == 1){
                result.setCode(200);
                result.setMessage("插入成功");
            }else {
                result.setCode(500);
                result.setMessage("插入失败");
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
        int count = userDao.countUser(userName,userRole);
        return count > 0 ? true : false;
    }

}
