package com.record.tcgl.service;

import com.record.tcgl.vo.ResultVo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/9 16:08
 **/
public interface UserService {
    ResultVo<Boolean> adminRoles(String userName, Integer userRole, String passWord);
}
