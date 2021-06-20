package com.record.tcgl.controller.security;

import com.record.tcgl.entity.UserEntity;
import com.record.tcgl.service.UserService;
import com.record.tcgl.vo.ResultVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.commands.BinaryJedisCommands;

import javax.annotation.Resource;

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
    private UserService userService;

    /**
     * @description TODO 登录获取token
     * @param userEntity
     * @return com.record.tcgl.vo.ResultVo<java.lang.String>
     * @author Shuguang_Liux
     * @date 2021/6/4 21:43
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultVo<String> login(UserEntity userEntity) {
        ResultVo<Boolean> boo = userService.userRoles(userEntity);
        ResultVo<String> resultVo = new ResultVo<>();
        String userName = userEntity.getUsername();
        if (boo.getSuccess()){
//            String token = DigestUtils.md5Hex(userEntity.getUserName() + System.currentTimeMillis());
//            resultVo.setResult(token);
//            resultVo.setMessage("当前登录用户"+userName);
//            BinaryJedisCommands binaryJedisCommands = jedisPool.getResource();
//            String redisToken = RedisFlagContast.AUTH_KEY_PREFIX + token;
//            binaryJedisCommands.set(userName.getBytes(),redisToken.getBytes());
//            binaryJedisCommands.expire(userName.getBytes(), RedisFlagContast.ACCOUNT_TOKEN_TIMEOUT);
//            byte[] userNameTokenbyte = redisToken.getBytes();
//            //存在
//            if (binaryJedisCommands.exists(userName.getBytes())) {
//                //获取对应的value
//                byte[] userNameToken = binaryJedisCommands.get(userName.getBytes());
//                if (userNameToken != null) {
//                    binaryJedisCommands.del(userNameToken);
//                    binaryJedisCommands.set(redisToken.getBytes(), userNameTokenbyte);
//                    binaryJedisCommands.expire(redisToken.getBytes(),
//                            RedisFlagContast.ACCOUNT_TOKEN_TIMEOUT);
//                }
//            } else {
//                binaryJedisCommands.set(redisToken.getBytes(), userNameTokenbyte);
//                binaryJedisCommands.expire(redisToken.getBytes(),
//                        RedisFlagContast.ACCOUNT_TOKEN_TIMEOUT);
//            }
        }else {
            resultVo.setError(400,"用户名或密码不对");
        }
        return resultVo;
    }

}
