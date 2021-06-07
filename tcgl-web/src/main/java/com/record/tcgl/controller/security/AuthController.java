package com.record.tcgl.controller.security;

import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.service.UserService;
import com.record.tcgl.util.Md5utils;
import com.record.tcgl.util.TokenUtils;
import com.record.tcgl.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.webConfig.controller
 * @Description ToDo 登
 * @Date 2021/3/28 21:43
 **/
@RestController
@RequestMapping("web")
public class AuthController {

    @Resource
    private TokenUtils tokenUtils;

    @Resource
    private UserService userService;

    @Resource
    private Md5utils md5utils;

    /**
     * @description TODO 登录获取token
     * @param userEntity
     * @return com.record.tcgl.vo.ResultVo<java.lang.String>
     * @author Shuguang_Liux
     * @date 2021/6/4 21:43
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultVo<String> login(UserEntity userEntity) throws NoSuchAlgorithmException {
        ResultVo<Boolean> boo = userService.userRoles(userEntity);
        ResultVo<String> resultVo = new ResultVo<>();
        String userName = userEntity.getUserName();
        String password = userEntity.getSecretCode();
        if (boo.getSuccess()){
            Jedis jedis = new Jedis("127.0.0.1",6379);
//            jedis.auth("12345");
            String token = md5utils.getMd5val(tokenUtils.getToken(userName,password));
            jedis.set(userName,token);
            //token过期时间
            jedis.expire(userName,600);
            jedis.set(token,userName);
            jedis.expire(token,600);
            long currentTime = System.currentTimeMillis();
            jedis.set(token+userName, Long.toString(currentTime));
            jedis.close();
            resultVo.setMessage("登录成功");
            resultVo.setResult(token);
        }else {
            resultVo.setError(400,"登录失败");
        }
        return resultVo;
    }

}
