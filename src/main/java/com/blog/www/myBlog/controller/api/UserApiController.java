package com.blog.www.myBlog.controller.api;

import com.blog.www.myBlog.dto.ResponseDto;
import com.blog.www.myBlog.model.RoleType;
import com.blog.www.myBlog.model.User;
import com.blog.www.myBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
        System.out.println("User API Controller : save call");
        // DB insert하고 return
        user.setRole(RoleType.USER);
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //
    }

    @PostMapping("api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
        System.out.println("Login call log");
        User principal = userService.login(user); // principal (접근 주체)

        if (principal != null) {
            session.setAttribute("principal : ", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //


    }
}
