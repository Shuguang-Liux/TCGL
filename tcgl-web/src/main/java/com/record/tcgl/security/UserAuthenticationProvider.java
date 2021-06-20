package com.record.tcgl.security;

import com.record.tcgl.entity.SysUserDetails;
import com.record.tcgl.service.LoadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户登录验证处理类
 * 
 * @author CL
 *
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private LoadUserService loadUserService;

	/**
	 * 身份验证
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal(); // 获取用户名
		String password = (String) authentication.getCredentials(); // 获取密码

		SysUserDetails sysUserDetails = loadUserService.loadUserByUsername(username);
		if (sysUserDetails == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}

		if (!new BCryptPasswordEncoder().matches(password, sysUserDetails.getPassword())) {
			throw new BadCredentialsException("用户名或密码错误");
		}

		if (sysUserDetails.getDeleteStatus().equals("1")) {
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
