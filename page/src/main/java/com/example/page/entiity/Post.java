package com.example.page.entiity;


import com.example.page.dtos.ChangeContext;
import com.example.page.dtos.PostRequestDto;
import com.example.page.dtos.PostResponseDto;

import com.example.page.entiity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped{
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


    public Post(User user,PostRequestDto postRequestDto) {
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
