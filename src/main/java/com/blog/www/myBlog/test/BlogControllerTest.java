package com.blog.www.myBlog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링이 해당 패키지 이하를 스캔해서 모든 팡리을 메모리에 new 하는것이 아니고,
// 특정 어노테이션이 붙어있는 클래스 파일들을 New해서 (IOC) 스프링 컨테이너에 관리해준다.
//@Controller

@RestController
public class BlogControllerTest {

	@GetMapping("/test/hello")
	public String hello() {
		return "Hello Spring Boot";
	}
}
