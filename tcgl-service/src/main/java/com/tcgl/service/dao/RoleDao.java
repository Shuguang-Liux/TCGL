package com.tcgl.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tcgl.serviceapi.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色Mapper
 *
 * @author Shuguang_Liux
 * @date 2021/06/23 10:34
 */
@Mapper
@Repository("RoleDao")
public interface RoleDao extends BaseMapper<Role> {

    List<Role> findRoleByUserId(Long userId);
}
