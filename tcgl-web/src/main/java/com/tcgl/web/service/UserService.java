package com.tcgl.web.service;


import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.UserEntity;

import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/9 16:08
 **/
public interface UserService {
    /**
     * @param userEntity
     * @return
     */
    ResultVo<Boolean> userRoles(UserEntity userEntity);

    /**
     * 根据用户名称更新用户密码
     * @return
     */
    ResultVo<Boolean> updateAccountInfo(UserEntity userEntity);

    /**
     * 用户注册服务
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
