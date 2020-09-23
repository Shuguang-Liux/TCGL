package com.record.tcgl.api;

import com.record.tcgl.vo.ResultVo;

import java.util.Map;

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
}
