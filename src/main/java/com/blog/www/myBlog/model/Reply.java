package com.blog.www.myBlog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id; //

    @Column(nullable = false, length = 200)
    private String content; // 어느 게시글에 있는 답변인지 모름

    @ManyToOne
    @JoinColumn(name = "boardId") // 여러개의 답변은 하나의 게시글에 존재할 수 있다.
    private Board board;

    @ManyToOne
    @JoinColumn(name="userId") // 하나의 유저는 여러개의 답변을 작성할 수 있다.
    private User user;

    @CreationTimestamp
    private Timestamp createDate;


}
