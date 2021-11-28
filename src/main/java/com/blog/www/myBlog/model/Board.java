package com.blog.www.myBlog.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
// Join을 알아서 한다

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @OneToMany(fetch=FetchType.LAZY) << 기본전략
// @MnayToOne(fetch=FetchType.EAGER) << 기본전략

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increaement
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트라는 라이브러리 <html> 태그가 섞여서 디자인됨

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Board -> Mnay // User -> One // 한명의 유저가 게시글을 쓸 수 있 수 있음 // 자동으로 FK키 생성됨
    @JoinColumn(name = "userId") // 실제 DB에 만들어지는 컬럼명
    private User user; // 작성자, board, user join... 하지않고 FK, 오브젝트 저장

    // (Reply 테이블에있는 board)
    @OneToMany(mappedBy = "board", fetch=FetchType.EAGER) // mappedBy = 연관관계의 주인이 아니다, FK가 아니라는 말임, DB에 컬럼을 만들지 말아주세요~
    // 그저 select 할때 값을 얻기위해서 필요한겁니다. 라고 선언한것
    // @JoinColmn(name ="replyId") 이게 필요없음 왜냐하면, Foreign키가 필요없음
    // 1정규화가 깨진다, 원자성
    // ex) Id | title | content | userid | createDate
    //      1    hi       hello     2       2020.5.17
    private List<Reply> reply; // 하나의 게시글에는 여러개의 댓글이 존재 할 수 있다.

    @CreationTimestamp
    private Timestamp createDate;
}
