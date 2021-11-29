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
}
