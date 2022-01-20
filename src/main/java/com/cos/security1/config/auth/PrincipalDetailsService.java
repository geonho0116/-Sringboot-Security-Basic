package com.cos.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

//시큐리티 설정에서 loginProcessingUrl("/login");
//로그인 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername함수가 실행된다.
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	//시큐리티세션에 Authentication에 UserDetails가 들어감
	@Override                                                    //로그인페이지에서 username으로 꼭 받아야한다. 그래야 여기로 들어간다. 바꾸려면 SecurityConfig에서 바꿔야함
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

}
