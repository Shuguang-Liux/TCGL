package com.tcgl.web.security;

import com.tcgl.web.security.entity.SysUserDetails;
import com.tcgl.web.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * 用户登录验证处理类
 *
 * @author Shuguang_Liux
 * @date 2021/06/23 17:19
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private SecurityService securityService;

	/**
	 * 身份验证
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 获取用户名
		String username = (String) authentication.getPrincipal();
		// 获取密码
		String password = (String) authentication.getCredentials();

		SysUserDetails sysUserDetails = securityService.loadUserByUsername(username);
		if (sysUserDetails == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		if (!new BCryptPasswordEncoder().matches(password, sysUserDetails.getPassword())) {
			throw new BadCredentialsException("用户名或密码错误");
		}

		if (1 == sysUserDetails.getDeleteStatus()) {
			throw new LockedException("用户已禁用");
		}

		return new UsernamePasswordAuthenticationToken(sysUserDetails, password, sysUserDetails.getAuthorities());
	}

	/**
	 * 支持指定的身份验证
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
