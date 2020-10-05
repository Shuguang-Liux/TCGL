package com.record.tcgl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.record.tcgl.api.UserRoleApi;
import com.record.tcgl.dao.UserDao;
import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.vo.ResultVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/9 17:02
 **/
@Service
@Component
public class UserRoleApiImpl implements UserRoleApi {

    @Autowired
    private UserDao userDao;

    /**
     * 用户角色权限登录
     * @param userName
     * @param userRole
     * @param passWord
     * @return
     */
    @Override
    public ResultVo<Boolean> checkAdminRole(String userName, Integer userRole, String passWord) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        queryWrapper.eq("user_role",userRole);
        queryWrapper.eq("user_password",passWord);
        Integer count = userDao.selectCount(queryWrapper);
        if (count > 0){
            resultVo.setResult(true);
            resultVo.setMessage("用户存在，正常登录");
        }else {
            resultVo.setResult(false);
            resultVo.setError(400,"用户不存在！");
        }
        return resultVo;
    }

    /**
     * 根据用户名查询（webservice测试使用）
     * @param userName
     * @return
     */
    @Override
    public UserEntity getUserInfo(String userName) {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("user_name",userName);
        return userDao.selectOne(userEntityQueryWrapper);
    }

}
