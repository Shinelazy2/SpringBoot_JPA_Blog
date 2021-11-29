package com.blog.www.myBlog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User Class를 읽어서 Mysql에 테이블이 생성된다.
// @DynamicInsert // insert 시에 null값이 있으면 쿼리에서 제외시켜준다.
public class User {
    @Id // PrimaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라간다. ex) auto_increment
    private int id; // sequence, auto_increment

    @Column(nullable = false, length = 30) // Notnull
    private String username;

    @Column(nullable = false,length = 100) // 넉넉하게 주는 이유는 hash로 암호화 하기 때문에
    private String password;

    //@ColumnDefault("'user'") << String 일때 이렇게 썼었음, null값이여서 제외하고 쿼리를 날렷었음
    @Enumerated(EnumType.STRING) // DB에는 RolType이라는게 없어서 String 이라고 알려 준다.
    private RoleType role; // Enum을 쓰는게 맞음, admin , user, mannger 권한을 주려고할때 오타가 날수있기때문임.

    @Column(nullable = false, length = 50)
    private String email;

    @CreationTimestamp // 시간 자동입력
    private Timestamp createDate;
}
