package com.example.page.entity;

import com.example.page.dto.ChangeContext;
import com.example.page.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

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
