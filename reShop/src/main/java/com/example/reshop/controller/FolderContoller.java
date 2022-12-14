package com.example.reshop.controller;

import com.example.reshop.dtos.folder.FolderRequestDto;
import com.example.reshop.entity.Folder;
import com.example.reshop.entity.Product;
import com.example.reshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderContoller {
    private final FolderService folderService;

    @PostMapping("/folders")
    public List<Folder> addFolders(
            @RequestBody FolderRequestDto folderRequestDto,
            HttpServletRequest request
    ) {

        List<String> folderNames = folderRequestDto.getFolderNames();

        return folderService.addFolders(folderNames, request);
    }

    @GetMapping("/folders")
    public List<Folder> getFolder(
            HttpServletRequest request
    ){
        return folderService.getFolders(request);
    }

    // 회원이 등록한 폴더 내 모든 상품 조회
    @GetMapping("/folders/{folderId}/products")
    public Page<Product> getProductsInFolder(
            @PathVariable Long folderId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean isAsc,
            HttpServletRequest request
    ) {
        return folderService.getProductsInFolder(
                folderId,
                page - 1,
                size,
                sortBy,
                isAsc,
                request
        );
    }
}
