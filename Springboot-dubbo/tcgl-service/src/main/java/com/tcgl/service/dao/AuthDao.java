package com.tcgl.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tcgl.serviceapi.entity.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户权限mapper
 *
 * @author Shuguang_Liux
 * @date 2021/06/22 16:35
 */
@Mapper
@Repository("AuthDao")
public interface AuthDao extends BaseMapper<Auth> {
    /**
     * 根据用户ID获取事业部权限
     *
     * @param userId 用户ID
     * @return List<UserAuth>
     * @author Shuguang_Liux
     * @date 2021/6/22 16:55
    */
    List<Auth> findAuthByUserId(@Param("userId") Long userId);
}
