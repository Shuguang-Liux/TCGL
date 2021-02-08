package com.record.tcgl.service.impl;

import com.record.tcgl.api.UserRoleApi;
import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.service.UserService;
import com.record.tcgl.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;


/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service.impl
 * @Description ToDo
 * @Date 2020/9/9 16:09
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Reference(timeout = 10000,version = "1.0")
    private UserRoleApi userRoleApi;


    /**
     * 根据角色判断用户登录
     * @return
     */
    @Override
    public ResultVo<Boolean> userRoles(UserEntity userEntity) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        if(StringUtils.isEmpty(userEntity.getUserName())|| StringUtils.isEmpty(userEntity.getUserPassword())){
            resultVo.setError(400,"用户名或密码不正确！");
            return resultVo;
        }
        return userRoleApi.checkUserRole(userEntity);
    }

    /**
     * 根据用户名称更新用户密码
     * @return
     */
    @Override
    public ResultVo<Boolean> updateAccountInfo(UserEntity userEntity) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        if (StringUtils.isEmpty(userEntity.getUserName())||StringUtils.isEmpty(userEntity.getUserPassword())){
            resultVo.setError(400,"用户名或密码不能为空");
            return resultVo;
        }
        return userRoleApi.updateAccountInfo(userEntity);
    }

    @Override
    public ResultVo<?> register(UserEntity userEntity) {
        ResultVo<?> resultVo = new ResultVo<>();
        if (StringUtils.isEmpty(userEntity.getUserName()) ||
                StringUtils.isEmpty(userEntity.getUserPassword())||
                null == userEntity.getUserRole()){
            resultVo.setError(400,"信息不全");
            return resultVo;
        }
        return userRoleApi.register(userEntity);
    }

    @Override
    public ResultVo<Boolean> delete(String ids) {
        return null;
    }
}
