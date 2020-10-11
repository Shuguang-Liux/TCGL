package com.record.tcgl.api;

import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.vo.ResultVo;
import org.springframework.stereotype.Component;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.api
 * @Description ToDo
 * @Date 2020/9/9 16:59
 **/
public interface UserRoleApi {
    /**
     * 用户角色权限登录
     * @param userName
     * @param userRole
     * @param passWord
     * @return
     */
    ResultVo<Boolean> checkAdminRole(String userName, Integer userRole,String passWord);

    /**
     * 根据用户名查询（webservice测试使用）
     * @param userName
     * @return
     */
    UserEntity getUserInfo(String userName);

    /**
     * 根据用户名更新用户密码
     * @param userName
     * @param password
     * @return
     */
    ResultVo<Boolean> updatePassword(String userName,String password);
}
