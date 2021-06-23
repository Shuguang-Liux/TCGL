package com.record.tcgl.api;

import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.vo.ResultVo;

import java.util.List;
import java.util.Map;

/**
 * The interface User api.
 *
 * @author Shuguang_Liux
 * @package com.record.tcgl.api
 * @Description ToDo
 * @Date 2020 /9/9 16:59
 */
public interface UserApi {
    /**
     * 用户角色权限登录
     *
     * @param userEntity the user entity
     * @return result vo
     */
    ResultVo<Boolean> checkUserRole(UserEntity userEntity);

    /**
     * 根据用户名查询（webservice测试使用）
     *
     * @param userName the user name
     * @return user info
     */
    UserEntity getUserInfo(String userName);

    /**
     * 根据用户名更新用户密码
     *
     * @param userEntity the user entity
     * @return result vo
     */
    ResultVo<Boolean> updateAccountInfo(UserEntity userEntity);


    /**
     * 用户注册
     *
     * @param userEntity the user entity
     * @return result vo
     */
    ResultVo<?> register(UserEntity userEntity);

    /**
     * 删除用户信息
     *
     * @param ids the ids
     * @return result vo
     */
    ResultVo<Boolean> delete(String ids);

    /**
     * User list list.
     *
     * @return the list
     */
    List<Map<String,String>> userList();



}
