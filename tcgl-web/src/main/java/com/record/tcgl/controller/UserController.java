package com.record.tcgl.controller;

import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.service.UserService;
import com.record.tcgl.vo.ResultVo;
import com.record.tcgl.interfaces.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.controller
 * @Description ToDo
 * @Date 2020/9/14 15:43
 **/
@RestController
@RequestMapping("login")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 角色账户登录
     * @return
     */
    @RequestMapping(value = "submit",method = RequestMethod.POST)
    public ResultVo<Boolean> login(UserEntity param){
        return userService.userRoles(param);
    }

    /**
     * 用户注册
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResultVo<?> register(@RequestBody UserEntity userEntity){
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        return userService.register(userEntity);
    }

    /**
     * 密码更新
     * @param param
     * @return
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResultVo<Boolean> updatePassword(@RequestBody UserEntity userEntity){
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        return userService.updateAccountInfo(userEntity);
    }

}
