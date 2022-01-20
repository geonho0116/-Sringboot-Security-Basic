package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

//어노테이션없어도 jparepository를 상속했기 때문에 자동으로 빈등록됨 
public interface UserRepository extends JpaRepository<User, Integer>{ //타입, 프라이머리키
	//findBy까지 규칙이 있고 그 뒤 Username은 문법
	//select * from user where username=1? 이 실행된다. : JPA쿼리메서드
	public User findByUsername(String username);
}
