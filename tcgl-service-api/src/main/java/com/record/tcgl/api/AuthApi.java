package com.record.tcgl.api;

import com.record.tcgl.entity.Auth;

import java.util.List;


/**
 * 用户身份验证api
 *
 * @author Shuguang_Liux
 * @date 2021/06/23
 */
public interface AuthApi {


    /**
     * 找到身份验证的用户id
     *
     * @param userId 用户id
     * @return {@link List<Auth>}
     */
    List<Auth> findAuthByUserId(Long userId);

}
