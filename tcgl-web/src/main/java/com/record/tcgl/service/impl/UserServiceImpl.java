package com.record.tcgl.service.impl;

import com.record.tcgl.api.UserRoleApi;
import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.service.UserService;
import com.record.tcgl.vo.ResultVo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service.impl
 * @Description ToDo
 * @Date 2020/9/9 16:09
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Reference()
    private UserRoleApi userRoleApi;


    /**
     * 根据角色判断用户登录
     * @return
     */
    @Override
    public ResultVo<Boolean> adminRoles(UserEntity userEntity) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        if(StringUtils.isEmpty(userEntity.getUserName())||StringUtils.isEmpty(userEntity.getUserPassword())){
            resultVo.setError(400,"用户名或密码不正确！");
            return resultVo;
        }
        if (StringUtils.isEmpty(userEntity.getUserRole())){
            resultVo.setError(400,"角色信息不正确！");
            return resultVo;
        }
        return userRoleApi.checkAdminRole(userEntity);
    }

    /**
     * 根据用户名称更新用户密码
     * @return
     */
    @Override
    public ResultVo<Boolean> updatePassword(UserEntity userEntity) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        if (StringUtils.isEmpty(userEntity.getUserName())||StringUtils.isEmpty(userEntity.getUserPassword())){
            resultVo.setError(400,"用户名或密码不能为空");
            return resultVo;
        }
        return userRoleApi.updatePassword(userEntity);
    }
}
