package com.record.tcgl.controller;

import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.service.UserService;
import com.record.tcgl.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResultVo<Boolean> adminRoleLogin(@RequestBody UserEntity userEntity){
        return userService.login(userEntity);
    }

    /**
     * 密码更新
     * @param param
     * @return
     */
    @RequestMapping("/adminPassword")
    public ResultVo<Boolean> updatePassword(@RequestBody UserEntity param){
        return userService.updatePassword(param);
    }


    /**
     *
     * @return
     */
    @RequestMapping("/uu")
    public String uu(){
        return "1";
    }

}
