package com.my.member_app.controller;

import com.my.member_app.dto.MemberDto;
import com.my.member_app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/view")
    public String showAllMember(Model model) {
        List<MemberDto> dtoList = memberService.findAll();
        model.addAttribute("title", "회원정보");
        model.addAttribute("lists", dtoList);
        return "showMember";
    }
}
