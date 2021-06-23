package com.record.tcgl.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.record.tcgl.api.UserApi;
import com.record.tcgl.dao.UserDao;
import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.vo.ResultVo;
import org.apache.dubbo.config.annotation.DubboService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo 
 * @Date 2020/9/9 17:02
 **/
@DubboService
@Component
public class UserApiImpl implements UserApi {

    private final static Logger logger = LoggerFactory.getLogger(UserApiImpl.class);

    @Autowired
    private UserDao userDao;

    /**
     * 用户角色权限登录
     * @return
     */
    @Override
    public ResultVo<Boolean> checkUserRole(UserEntity userEntity) {
        ResultVo<Boolean> resultVo = new ResultVo<>();
        try {
            LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(UserEntity::getUsername,userEntity.getUsername())
                    .eq(UserEntity::getPassword,userEntity.getPassword());
            Integer count = userDao.selectCount(lambdaQueryWrapper);
            if (count > 0){
            }else {
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
     * 根据用户名查询
     * @param userName
     * @return
     */
    @Override
    public UserEntity getUserInfo(String userName) {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("username",userName);
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
        queryWrapper.eq("username",userEntity.getUsername());
        int rowNum = userDao.selectCount(queryWrapper);
        if (1 != rowNum){
            resultVo.setError(400,"更新失败，请联系管理员");
            return resultVo;
        }
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        try {
            updateWrapper.eq("username",userEntity.getUsername());
            userEntity.setPassword(userEntity.getPassword());
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
        if (isExist(userEntity.getUsername())){
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

    @Override
    public List<Map<String, String>> userList() {
        List<UserEntity> list = userDao.selectList(new UpdateWrapper<UserEntity>().eq("username","admin").set("delete_status","Y"));
        userDao.update(null,new QueryWrapper<UserEntity>().eq("username","admin"));
//        List<Map<String,String>> map = list.stream().collect(Collectors.toMap(UserEntity::getUserName,UserEntity::getPassword))
//       Map<Integer,UserEntity> map = list.stream().filter(o -> "1".equals(o.getUsername())).collect(Collectors.toMap(UserEntity::getId,userEntity -> userEntity,(k1,k2) -> k1));

       List<Map<String,String>> mapList = list.stream().map(userEntity ->
       { Map<String,String> remap = new HashMap<>(2);
           remap.put("username",userEntity.getUsername());
           remap.put("password",userEntity.getPassword());
           return remap;
        }).collect(Collectors.toList());

       list.forEach(o -> {
           o.setDeleteStatus(0);
       });
       return null;
     }

    /**
     * 用户名称存在校验
     * @param userName
     * @return
     */
    private Boolean isExist(String userName){
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        int count = userDao.selectCount(queryWrapper);
        return count > 0;
    }
}
