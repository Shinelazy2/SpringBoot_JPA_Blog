package com.blog.www.myBlog.repository;

import com.blog.www.myBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// 자동으로 bean 등록이 된다.
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
    // jPA Naming 전략
    // 쿼리가 and를 넣으면서 쿼리가 실행됨
    // select * from user where username =?1 and password =?2;

    User findByUsernameAndPassword(String username, String password);


    // 이렇게도 사용이 가능하다~
/*    @Query(value = "select * from user where username =?1 and password =?2", nativeQuery = true)
    User login(String username, String password);*/

}
