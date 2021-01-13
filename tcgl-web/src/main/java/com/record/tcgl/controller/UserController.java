package com.record.tcgl.controller;

import com.alibaba.fastjson.JSONObject;
import com.record.tcgl.service.UserService;
import com.record.tcgl.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.controller
 * @Description ToDo
 * @Date 2020/9/14 15:43
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 角色账户登录
     * @return
     */
    @RequestMapping("/roleLogin")
    public ResultVo<Boolean> adminRoleLogin(@RequestBody JSONObject param){
        String userName = param.getString("username");
        Integer userRole = param.getInteger("code");
        String passWord = param.getString("password");
        return userService.adminRoles(userName,userRole,passWord);
    }

    /**
     * 密码更新
     * @param param
     * @return
     */
    @RequestMapping("/adminPassword")
    public ResultVo<Boolean> updatePassword(@RequestBody JSONObject param){
        String userName = param.getString("userName");
        String passWord = param.getString("passWord");
        return userService.updatePassword(userName,passWord);
    }
//    @RequestMapping("/delete")
//    public ResultVo<Boolean> deleteUser(@RequestBody JSONObject param){
//
//    }

    /**
     *
     * @return
     */
    @RequestMapping("/uu")
    public String uu(){
        return "1";
    }
}
