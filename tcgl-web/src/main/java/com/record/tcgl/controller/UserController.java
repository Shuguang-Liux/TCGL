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
    @RequestMapping("/roleogin")
    public ResultVo<Boolean> adminRoleLogin(@RequestBody JSONObject param){
        String userName = param.getString("userName");
        Integer userRole = param.getInteger("userRole");
        String passWord = param.getString("passWord");
        return userService.adminRoles(userName,userRole,passWord);
    }
}
