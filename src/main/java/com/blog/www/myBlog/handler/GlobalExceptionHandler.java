package com.blog.www.myBlog.handler;

import com.blog.www.myBlog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 exception이 이쪽으로 온다.
@RestController
public class GlobalExceptionHandler {

    // 밑에 어노테이션 설정해주면 IlegalArgumentException에 대해 해당 메시지 출력
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>" + e.getMessage() + "</h1>";
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> handleArgumentException(Exception e){
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }


}
