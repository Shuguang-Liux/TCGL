package com.tcgl.serviceapi.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Map;

/**
 * 用户api
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
    Boolean checkUserCount(UserEntity userEntity);

    /**
     * 根据用户名查询（webservice测试使用）
     *
     * @param userName the user name
     * @return user info
     */
    UserEntity getUserInfoByName(String userName);

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

    /**
     * 用户列表
     *
     * @param userEntity 用户实体
     * @return {@link List}<{@link UserEntity}>
     */
    IPage<UserEntity> userPage(UserEntity userEntity);



}
