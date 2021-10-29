package com.tcgl.web.security.Handler;

import com.tcgl.web.security.entity.SysUserDetails;
import com.tcgl.web.security.utils.AccessAddressUtils;
import com.tcgl.web.security.utils.JWTTokenUtils;
import com.tcgl.web.security.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理类
 * 
 * @author CL
 *
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
		// 获得请求IP
		String ip = AccessAddressUtils.getIpAddress(request);
		sysUserDetails.setIp(ip);
		String token = JWTTokenUtils.createAccessToken(sysUserDetails);

		// 保存Token信息到Redis中
		JWTTokenUtils.setTokenInfo(token, sysUserDetails.getUsername(), ip);
		
		log.info("用户{}登录成功，Token信息已保存到Redis", sysUserDetails.getUsername());

		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		ResponseUtils.responseJson(response, ResponseUtils.response(200, "登录成功", tokenMap));
	}
}
