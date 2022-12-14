package com.example.reshop.repository;

import com.example.reshop.entity.Folder;
import com.example.reshop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    List<Folder> findAllByUser(User user);
}
