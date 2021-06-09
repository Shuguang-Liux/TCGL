package com.record.tcgl.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.record.tcgl.api.UserRoleApi;
import com.record.tcgl.dao.UserDao;
import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.vo.ResultVo;
import org.apache.dubbo.config.annotation.DubboService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
public class UserRoleApiImpl implements UserRoleApi {

    private final static Logger logger = LoggerFactory.getLogger(UserRoleApiImpl.class);

    @Resource
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
            lambdaQueryWrapper.eq(UserEntity::getUserName,userEntity.getUserName())
                    .eq(UserEntity::getSecretCode,userEntity.getSecretCode());
            Integer count = userDao.selectCount(lambdaQueryWrapper);
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
            userEntity.setSecretCode(userEntity.getSecretCode());
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

    @Override
    public List<Map<String, String>> userList() {
        List<UserEntity> list = userDao.selectList(new UpdateWrapper<UserEntity>().eq("user_name","admin").set("delete_status","Y"));
        userDao.update(null,new QueryWrapper<UserEntity>().eq("user_name","admin"));
//        List<Map<String,String>> map = list.stream().collect(Collectors.toMap(UserEntity::getUserName,UserEntity::getPassword))
       Map<Integer,UserEntity> map = list.stream().filter(o -> "1".equals(o.getUserName())).collect(Collectors.toMap(UserEntity::getId,userEntity -> userEntity,(k1,k2) -> k1));

       List<Map<String,String>> mapList = list.stream().map(userEntity ->
       { Map<String,String> remap = new HashMap<>(2);
           remap.put("user_name",userEntity.getUserName());
           remap.put("password",userEntity.getSecretCode());
           return remap;
        }).collect(Collectors.toList());

       list.forEach(o -> {
           o.setDeleteStatus("N");
           o.setUserRole(1);
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
        queryWrapper.eq("user_name",userName);
        int count = userDao.selectCount(queryWrapper);
        return count > 0;
    }
}
