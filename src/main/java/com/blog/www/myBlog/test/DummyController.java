package com.blog.www.myBlog.test;

import com.blog.www.myBlog.model.User;
import com.blog.www.myBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @Autowired // DummyController 메모리에 뜰떄 같이 뜬다. 의존성 주입!
    private UserRepository userRepository;

    //htpp:/localhost:8080/blog/dummy/join(요청)
    //http의 body에 username,password, email 데이터가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) { // key= value (약속된 규칙)
        System.out.println("username"+user.getUsername());
        System.out.println("password"+user.getPassword());
        System.out.println("email"+user.getEmail());
        System.out.println("role"+user.getRole());
        System.out.println("createDate"+user.getCreateDate());
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";

    }
}
