package com.blog.www.myBlog.service;

import com.blog.www.myBlog.model.User;
import com.blog.www.myBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// 스피링이 컴포넌트 스캔을 통해서 Bean에 등록해줌, IoC해준다~
// Service가 필요한 이유
// 트랜젝션 관리 ->
// 서비스 의미 떄문?
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void join(User user) {
        userRepository.save(user);
    }
}
