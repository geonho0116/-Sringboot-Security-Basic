package com.cos.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"/",""})
	public String index() {
		//머스테치 루트 : src/main/resources/
		//뷰리졸버 설정 : templates - prefix, .mustache - suffix =>생략가능
		return "index";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String user() {
		
		return "user";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		
		return "admin";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		 
		return "manager";
	}
	
	//login이면 스프링시큐리티로 가버림 -> SecurityConfig 생성하여 커스터마이징함
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encPassword);
		userRepository.save(user);
		
		
		return "redirect:/loginForm";
	}
	
}
