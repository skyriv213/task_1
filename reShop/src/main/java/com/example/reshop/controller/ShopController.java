package com.example.reshop.controller;

import com.example.reshop.service.FolderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController {
    private final FolderServiceImpl folderService;

    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index");
    }

    // 로그인 한 유저가 메인페이지를 요청할 때 가지고있는 폴더를 반환
    @GetMapping("/user-folder")
    public String getUserInfo(Model model, HttpServletRequest request) {

        model.addAttribute("folders", folderService.getFolders(request));

        return "/index :: #fragment";
    }

}
