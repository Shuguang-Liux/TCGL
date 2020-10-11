package com.record.tcgl.service;

import com.record.tcgl.vo.ResultVo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/9 16:08
 **/
public interface UserService {
    /**
     * 根据角色判断用户登录
     * @param userName
     * @param userRole
     * @param passWord
     * @return
     */
    ResultVo<Boolean> adminRoles(String userName, Integer userRole, String passWord);

    /**
     * 根据用户名称更新用户密码
     * @param userName
     * @param passWord
     * @return
     */
    ResultVo<Boolean> updatePassword(String userName,String passWord);

}
