package com.cos.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security1.model.User;

//시큐리티가 /login주소 요청이 오면 낚아채서 로그인을 진행하나.
//이때 로그인이 완료되면 session을 만들어준다. (Security ContextHolder라는 키값에 세션정보를 저장한다.)
//그 오브젝트는 Authentication타입의 객체여야한다(정해져있음)
//Authentication 안에 User정보가 있다. 이 User오브젝트의 타입은 UserDetails 타입이다.
//즉 Security세션 영역에 정보를 저장하는데, 들어갈 수 있는 객체는 Authentication타입으로 정해져있고. 이 안에 유저정보를 저장할 때 UserDetails타입이어야한다.
//
public class PrincipalDetails implements UserDetails{

	private User user; //컴포지션
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	
	//해당 유저의 권한을 리턴하는 곳.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
