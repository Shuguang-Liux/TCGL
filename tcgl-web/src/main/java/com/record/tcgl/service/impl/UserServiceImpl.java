package com.record.tcgl.service.impl;

import com.record.tcgl.api.UserRoleApi;
import com.record.tcgl.service.UserService;
import com.record.tcgl.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRoleApi userRoleApi;

    @Override
    public ResultVo<Boolean> adminRoles(String userName, Integer userRole, String passWord) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(passWord)){
            resultVo.setError(400,"用户名或密码不正确！");
            return resultVo;
        }
        if (StringUtils.isEmpty(userRole)){
            resultVo.setError(400,"角色信息不正确！");
            return resultVo;
        }
        return userRoleApi.checkAdminRole(userName,userRole,passWord);
    }
}
