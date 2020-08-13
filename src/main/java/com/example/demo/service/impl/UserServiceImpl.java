package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @package com.example.demo.service.impl
 * @Description ToDo
 * @Editor liuxiao
 * @Date 2020/7/16 9:20
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result getInfo(User user){
        Result result = new Result();
        result.setMessage("信息有误");
        if (StringUtils.isNotBlank(user.getUserName()) && StringUtils.isNotBlank(user.getUserPassword())){
            User userNew = new User();
            userNew.setUserName(user.getUserName());
            userNew.setUserPassword(user.getUserPassword());
            User userRes = userMapper.getInfo(userNew);
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

    @Override
    public Result updateUserInfo(User user){
        Result result = new Result();
        result.setMessage("更新失败");
        int count = userMapper.updateUserInfo(user);
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
            List<User> list = userMapper.getAllUserByUsername(user);
            if (CollectionUtils.isEmpty(list)){
                result.setMessage("用户不存在");
            }else {
                for (User user1 : list) {
                    user1.setDeleteState("Y");
                    userMapper.updateByPrimaryKey(user1);
                }
                result.setMessage("更新成功");
            }
            return result;
        }
        return result;

    }
}
