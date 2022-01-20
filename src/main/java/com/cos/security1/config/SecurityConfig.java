package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록된다
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//패스워드암호화를빈등록
	//해당 메서드의 리턴되는 오브젝트를 빈등록한다.
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf토큰 비활성화 -> 테스트할 때 해놓는게 좋음
		http.authorizeRequests() 
				.antMatchers("/user/**").authenticated() // 인증돼야함
				.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/loginForm") // 권한이 없을 경우 로그인페이지로 이동
				.loginProcessingUrl("/login") // login주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행한다.
				.defaultSuccessUrl("/"); //유저로 들어가서 로그인하면 자동으로 유저로 보내준다.
//			.and() //그리고
//				.formLogin()
//				.loginPage("/auth/loginForm") //로그인페이지
//				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 오는 로그인을 가로채서 대신 로그인을 수행한다.
//				.defaultSuccessUrl("/");
	}
	
}
