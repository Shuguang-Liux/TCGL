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

import java.util.Arrays;

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
            queryWrapper.eq("user_name",userEntity.getUserName())
            .eq("user_role",userEntity.getUserRole())
            .eq("user_password",userEntity.getUserRole());
            Integer count = userDao.selectCount(queryWrapper);
            if (count > 0){
                resultVo.setResult(true);
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
    public ResultVo<Boolean> updateAccountInfo(UserEntity userEntity) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userEntity.getUserName());
        int rowNum = userDao.selectCount(queryWrapper);
        if (1 != rowNum){
            resultVo.setError(400,"更新失败，请联系管理员");
            return resultVo;
        }
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        try {
            updateWrapper.eq("user_name",userEntity.getUserName());
            userEntity.setUserPassword(userEntity.getUserPassword());
            //mybatisPlus使用构造求更新需要一个实体
            userDao.update(userEntity,updateWrapper);
            resultVo.setMessage("更新成功");
        } catch (Exception e) {
            logger.error("更新失败"+e.getMessage());
            e.printStackTrace();
            resultVo.setError(400,"更新失败，请联系管理员");
        }
        return resultVo;
    }

    @Override
    public ResultVo<?> register(UserEntity userEntity) {
        ResultVo<?> resultVo = new ResultVo<>();
        if (isExist(userEntity.getUserName())){
          resultVo.setError(400,"用户名称已存在");
          return resultVo;
        }
        try {
            userDao.insert(userEntity);
        } catch (Exception e) {
            logger.error("插入失败"+e.getMessage());
            e.printStackTrace();
            resultVo.setError(400,"注册失败，请联系管理员");
        }
        return resultVo;
    }

    @Override
    public ResultVo<Boolean> delete(String ids) {
        userDao.selectBatchIds(Arrays.asList(ids.split(",")));
        return new ResultVo<>();
    }

    /**
     * 用户名称存在校验
     * @param userName
     * @return
     */
    private Boolean isExist(String userName){
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        int count = userDao.selectCount(queryWrapper);
        return count != 0;
    }
}
