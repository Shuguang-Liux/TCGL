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
     * @return
     */
    ResultVo<Boolean> checkAdminRole(UserEntity userEntity);

    /**
     * 根据用户名查询（webservice测试使用）
     * @param userName
     * @return
     */
    UserEntity getUserInfo(String userName);

    /**
     * 根据用户名更新用户密码
     * @return
     */
    ResultVo<Boolean> updatePassword(UserEntity userEntity);

//    public  testPatternPlan(String patternPlan);

}
