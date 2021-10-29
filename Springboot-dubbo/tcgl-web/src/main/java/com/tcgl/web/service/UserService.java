package com.tcgl.web.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.entity.UserEntity;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/9 16:08
 **/
public interface UserService {

    /**
     * 检查用户
     *
     * @param userEntity 用户实体
     * @return {@link ResultVo}<{@link Boolean}>
     */
    ResultVo<Boolean> checkUser(UserEntity userEntity);

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

    /**
     * 页面
     *
     * @param userEntity 用户实体
     * @return {@link IPage}<{@link UserEntity}>
     */
    IPage<UserEntity> page(UserEntity userEntity);

}
