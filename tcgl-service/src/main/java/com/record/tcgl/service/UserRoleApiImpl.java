package com.record.tcgl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.record.tcgl.api.UserRoleApi;
import com.record.tcgl.dao.UserDao;
import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.vo.ResultVo;
import org.apache.dubbo.config.annotation.Service;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/9 17:02
 **/
@Service
@Component
public class UserRoleApiImpl implements UserRoleApi {

    private final static Logger logger = LogManager.getLogger(UserRoleApiImpl.class);

    @Autowired
    private UserDao userDao;

    /**
     * 用户角色权限登录
     * @return
     */
    @Override
    public ResultVo<Boolean> checkUserRole(UserEntity userEntity) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        try {
            queryWrapper.eq("user_name",userEntity.getUserName());
            queryWrapper.eq("user_role",userEntity.getUserRole());
            queryWrapper.eq("user_password",userEntity.getUserRole());
            Integer count = userDao.selectCount(queryWrapper);
            if (count > 0){
                resultVo.setResult(true);
                resultVo.setMessage("用户存在，正常登录");
            }else {
                resultVo.setResult(false);
                resultVo.setError(400,"用户不存在！");
            }
        } catch (Exception e) {
            logger.error("角色登录失败"+e.getMessage());
            e.printStackTrace();
            resultVo.setError(400,"登录失败请联系管理员");

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

    /**
     * 根据用户名更新用户密码
     *
     * @return
     */
    @Override
    public ResultVo<Boolean> updatePassword(UserEntity userEntity) {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        ResultVo<Boolean> resultVo = new ResultVo<>();
        try {
            updateWrapper.eq("user_name",userEntity.getUserName());
            userEntity.setUserPassword(userEntity.getUserPassword());
            //mybatisPlus使用构造求更新需要一个实体
            userDao.update(userEntity,updateWrapper);
            resultVo.setMessage("更新成功");
        } catch (Exception e) {
            logger.error("更新失败"+e.getMessage());
            e.printStackTrace();
            resultVo.setError(400,"更新失败请联系管理员");
        }
        return resultVo;
    }
}
