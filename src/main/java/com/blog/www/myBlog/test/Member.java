package com.blog.www.myBlog.test;

import lombok.*;

//getter, setter
@Data
//@AllArgsConstructor // 전부 생성자
@NoArgsConstructor // 인스턴스 생성해줌
public class Member {
	// 변경할일이 없으면 final로 하고
	// @RequiredArgsConstructor 사용한다.
	private  int id;
	private  String username;
	private  String password;
	private  String email;

	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
