package com.tcgl.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tcgl.common.exception.BaseBusinessException;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.serviceapi.api.UserApi;
import com.tcgl.serviceapi.entity.UserEntity;
import com.tcgl.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service.impl
 * @Description ToDo
 * @Date 2020/9/9 16:09
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @DubboReference
    private UserApi userApi;


    /**
     * 检查用户
     *
     * @param userEntity 用户实体
     * @return {@link ResultVo}<{@link Boolean}>
     */
    @Override
    public ResultVo<Boolean> checkUser(UserEntity userEntity) {
        if(StringUtils.isEmpty(userEntity.getUsername())|| StringUtils.isEmpty(userEntity.getPassword())){
            throw new BaseBusinessException("请输入用户名和密码");
        }
        if (!userApi.checkUserCount(userEntity)){
            return ResultVo.fail("账户不存在");
        }
        return ResultVo.ok();
    }

    /**
     * 根据用户名称更新用户密码
     * @return
     */
    @Override
    public ResultVo<Boolean> updateAccountInfo(UserEntity userEntity) {
        if(Objects.isNull(userEntity.getId())){
            throw new BaseBusinessException("请传入ID");
        }
        if (StringUtils.isEmpty(userEntity.getUsername())||StringUtils.isEmpty(userEntity.getPassword())){
            throw new BaseBusinessException("用户名或密码不能为空");
        }
        if (Objects.nonNull(userApi.getUserInfoByName(userEntity.getUsername())) ){
            throw new BaseBusinessException("用户不存在");
        }
        return userApi.updateAccountInfo(userEntity);
    }

    @Override
    public ResultVo<?> register(UserEntity userEntity) {
        if (StringUtils.isEmpty(userEntity.getUsername()) ||
                StringUtils.isEmpty(userEntity.getPassword())){
            throw new BaseBusinessException("请输入用户信息");
        }
        if (Objects.nonNull(userApi.getUserInfoByName(userEntity.getUsername()))){
            throw new BaseBusinessException("用户名已存在");
        }
        return userApi.register(userEntity);
    }

    /**
     * 删除(多个)
     *
     * @param ids id
     * @return {@link ResultVo}<{@link Boolean}>
     */
    @Override
    public ResultVo<Boolean> delete(String ids) {
        return userApi.delete(ids);
    }

    /**
     * 页面
     *
     * @param userEntity 用户实体
     * @return {@link IPage}<{@link UserEntity}>
     */
    @Override
    public IPage<UserEntity> page(UserEntity userEntity) {
        return userApi.userPage(userEntity);
    }
}
