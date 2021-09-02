package com.tcgl.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.UserEntity;
import com.tcgl.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.controller.admin
 * @Description 管理员控制类
 * @Date 2021/1/21 0:10
 **/
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 检查管理员账户
     *
     * @param userEntity 用户实体
     * @return {@link ResultVo}<{@link Boolean}>
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultVo<Boolean> checkAdminAccount(@RequestBody UserEntity userEntity){
        return userService.checkUser(userEntity);
    }


    /**
     * 更新账户信息
     *
     * @param userEntity 用户实体
     * @return {@link ResultVo}<{@link ?}>
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResultVo<?> updateAccountInfo(@RequestBody UserEntity userEntity){
        return userService.updateAccountInfo(userEntity);
    }


    /**
     * 删除用户信息
     *
     * @param ids id
     * @return {@link ResultVo}<{@link Boolean}>
     */
    @RequestMapping(value = "delete/{ids}",method = RequestMethod.DELETE)
    public ResultVo<Boolean> deleteUserInfo(@PathVariable String ids){
        return userService.delete(ids);
    }

    /**
     * 页面
     *
     * @param userEntity 用户实体
     * @return {@link ResultVo}<{@link IPage}<{@link UserEntity}>>
     */
    @RequestMapping(value = "page",method = RequestMethod.POST)
    public ResultVo<IPage<UserEntity>> page(UserEntity userEntity){
        return new ResultVo<IPage<UserEntity>>().of(userService.page(userEntity));
    }




}
