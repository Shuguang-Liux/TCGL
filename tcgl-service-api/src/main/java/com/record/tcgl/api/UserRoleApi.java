package com.record.tcgl.api;

import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.vo.ResultVo;
import org.apache.catalina.User;
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
    ResultVo<Boolean> checkUserRole(UserEntity userEntity);

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
    ResultVo<Boolean> updateAccountInfo(UserEntity userEntity);


    /**
     * 用户注册
     * @param userEntity
     * @return
     */
    ResultVo<?> register(UserEntity userEntity);

    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    ResultVo<Boolean> delete(String ids);
}
