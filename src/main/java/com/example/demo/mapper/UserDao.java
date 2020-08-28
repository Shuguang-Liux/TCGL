package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getInfo(User user);

    /**
     * 根据用户名更新用户信息
     * @param user
     * @return
     */
    int updateUserInfo(User user);

    /**
     * 根据用户名查询用户列表
     * @param user
     * @return
     */
    List<User> getAllUserByUsername(User user);

    /**
     * 管理员插入用户
     * @param user
     * @return
     */
    int insertUserInfo(User user);

    /**
     * 用户角色和用户名查询用户是否存在
     * @param userName
     * @param userRole
     * @return
     */
    int countUser(@Param("userName")String userName,@Param("userRole")int userRole);
}