package com.example.page.entity.user;

import com.example.page.entity.Comment;
import com.example.page.entity.Post;
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
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    public User(String name, String password, Grade grade) {
        this.username = name;
        this.password = password;
        this.grade = grade;
    }
}
