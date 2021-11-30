package com.blog.www.myBlog.test;

import com.blog.www.myBlog.model.RoleType;
import com.blog.www.myBlog.model.User;
import com.blog.www.myBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController

public class DummyController {

    @Autowired // DummyController 메모리에 뜰떄 같이 뜬다. 의존성 주입!
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        // try, catch를 걸어줘야 한다.
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            return "삭제에 실패했습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. : " + id;
    }

    // email, password
    @Transactional // 더티체킹 - 함수종료시 자동으로 commit 된다.
    @PutMapping("/dummy/user/{id}")
    // @RequestBody , Json data를 받을때 사용함. save로 업데이트 하는 소스
    // save 함수는 id를 전달하지 않으면 insert를 해주고
    // id를 전달할때 데이터가 있으면 update
    // id를 전달할때 데이터가 없으면 Insert
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id : " + requestUser.getId());
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());
        requestUser.setId(id);
        requestUser.setUsername("roro");

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

//        user.setPassword(requestUser.getPassword());
//        user.setEmail((requestUser.getEmail()));
        //update할때는 save를 잘 쓰지 않는다.
//        userRepository.save(user);

        return user;

    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    // 한페이지당 2건의 데이터를 리턴받아 볼거임
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2, sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        System.out.println(pagingUser.getTotalPages());
        List<User> users = pagingUser.getContent();
        return users;

    }


    // {id}로 파라미터 받는다.
    // 주석감은것처럼 할 수도 있음.
    /*@GetMapping("/dummy/user/{id}")
    // @PathVariable로 {id}값 받음
    public User detail(@PathVariable int id) {
        // 예를 들어 user/4를 찾았을때 DB에서 못찾아오면 user가 null이 되니깐 오류발생할 수 있어
        // 그래서 Optional로 user객체를 가져올테니 null인지 아닌지 판단해서 return 하렴
        User user =  userRepository.findById(id).orElseGet(new Supplier<User>() {
            // 비정상적이면 빈 객체를 리턴해라, null 리턴 못하게끔
            @Override
            public User get() {
                return new User();
            }

        }); // findById 리턴 타입이 Optional
        return user;
    }*/
    //람다식으로 하는방법
    /*
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> {
                    return new IllegalArgumentException("해당 사용자는 없습니다." + id);
                });
        return user;
    }*/

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. : " + id);
            }

        });

        return user;
    }


    //htpp:/localhost:8080/blog/dummy/join(요청)
    //http의 body에 username,password, email 데이터가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) { // key= value (약속된 규칙)
        //user,password,email만 받은이유는 나머지는 자동으로 입력되기 때문에
        System.out.println("username"+user.getUsername());
        System.out.println("password"+user.getPassword());
        System.out.println("email"+user.getEmail());
        System.out.println("role"+user.getRole());
        System.out.println("createDate"+user.getCreateDate());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";

    }
}
