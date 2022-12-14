package com.example.reshop.controller;

import com.example.reshop.dtos.folder.FolderRequestDto;
import com.example.reshop.entity.Folder;
import com.example.reshop.service.FolderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderContoller {
    private final FolderServiceImpl folderService;

    @PostMapping("/folders")
    public List<Folder> addFolders(
            @RequestBody FolderRequestDto folderRequestDto,
            HttpServletRequest request
    ) {

        List<String> folderNames = folderRequestDto.getFolderNames();

        return folderService.addFolders(folderNames, request);
    }
}
