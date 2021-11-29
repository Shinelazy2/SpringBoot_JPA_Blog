package com.blog.www.myBlog.repository;

import com.blog.www.myBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 자동으로 bean 등록이 된다.
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
}
