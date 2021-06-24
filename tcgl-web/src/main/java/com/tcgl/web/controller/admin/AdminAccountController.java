package com.tcgl.web.controller.admin;

import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.UserEntity;
import com.tcgl.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.controller.admin
 * @Description ToDo 管理员控制类
 * @Date 2021/1/21 0:10
 **/
@RestController
@RequestMapping("admin")
public class AdminAccountController {

    @Autowired
    private UserService userService;

    /**
     * @Author Shuguang_Liux
     * @Description TODO 管理员账户登录
     * @Date 2021/4/6 0:16
     * @Param [userEntity]
     * @return com.record.tcgl.vo.ResultVo<java.lang.Boolean>
     **/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultVo<Boolean> checkAdminAccount(@RequestBody UserEntity userEntity){
        return userService.userRoles(userEntity);
    }

    /**
     * @Author Shuguang_Liux
     * @Description  管理员用户更新账户密码
     * @Date 23:34 2021/4/5
     * @Param [userEntity]
     * @return com.record.tcgl.vo.ResultVo<?>
     **/
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultVo<?> updateAccountInfo(@RequestBody UserEntity userEntity){
        return userService.updateAccountInfo(userEntity);
    }

    /**
     * @Author Shuguang_Liux
     * @Description TODO 删除用户信息
     * @Date 2021/4/6 14:42
     * @Param [ids]
     * @return com.record.tcgl.vo.ResultVo<java.lang.Boolean>
     **/
   @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
    public ResultVo<Boolean> deleteUserInfo(@PathVariable String ids){
        return null;
    }
}
