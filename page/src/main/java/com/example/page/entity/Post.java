package com.example.page.entity;

import com.example.page.dto.post.ChangeContext;
import com.example.page.dto.post.PostRequestDto;
import com.example.page.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 단방향 조회
     * sql, ddl 직접작성,auto crate 해제
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    public Post(User user, PostRequestDto postRequestDto) {
        this.user = user;
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();

    }


    public void update(ChangeContext changeContext)
    {
        this.title = changeContext.getTitle();
        this.content = changeContext.getContent();
    }
}
