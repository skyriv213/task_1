package com.example.reshop.entity;

import com.example.reshop.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    // 독립적 설계를 추구, db, back 서로 독립적, 외래키 생성시 코드단에서 에러 발생 가능 ↑, 하나의 케이스

    public Folder(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
