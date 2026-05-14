package com.my.member_app.controller;

import com.my.member_app.dto.MemberDto;
import com.my.member_app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/view")
    public String showAllMember(Model model) {
        List<MemberDto> dtoList = memberService.findAll();
        model.addAttribute("title", "회원정보");
        model.addAttribute("lists", dtoList);
        return "showMember";
    }

    @GetMapping("/insertForm")
    public String insertForm(Model model){
        // 모델에 빈 Dto를 생성해서 보낸다.
        model.addAttribute("dto", new MemberDto());
        return "insertMember";
    }

    @PostMapping("/insert")
    // 리다이렉트용 1회성 모델에 담아 보내는 기능 : RedirectAttributes
    public String insertMember(@ModelAttribute("dto") MemberDto dto,
                               RedirectAttributes redirectAttributes){
        // redirect:/member/view는 /member/view를 Get으로 다시 호출
        log.info("result : " + dto);
        // insert 서비스를 호출
        memberService.insert(dto);
        // 성공 메시지를 RedirectAttributes에 담아 보낸다.
        redirectAttributes.addFlashAttribute("message",
                "등록이 완료되었습니다.");
        return "redirect:/member/view";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("deleteId")long deleteId,
                         RedirectAttributes redirectAttributes){
        log.info("=====   deleteId = " + deleteId);
        memberService.delete(deleteId);
        redirectAttributes.addFlashAttribute("message",
                "정상적으로 삭제되었습니다.");
        return "redirect:/member/view";
    }

    @GetMapping("update")
    public String updateFormView(@RequestParam("updateId")long updateId,
                                 RedirectAttributes redirectAttributes, Model model){
        // 1. 선택한 id를 가져오는지 확인
        log.info("=====   updateID = " +updateId);
        // 2. 해당 id를 검색해서 dto로 받아온다.
        MemberDto memberDto = memberService.findById(updateId);
        if (memberDto == null) {
            // 3. 모델에 담아서 updateForm 에 보낸다.
            redirectAttributes.addFlashAttribute("message",
                    "수정되지 않았습니다.");
            return "redirect:/member/view";
        }
        model.addAttribute("member", memberDto);
        return "updateMember";
    }

    // update > post (업데이트 내용을 DB에 저장하는 작업)
    @PostMapping("/update")
    public String update(@ModelAttribute("dto")MemberDto dto,
                         RedirectAttributes redirectAttributes){
        // 찍어보기
        log.info("updateDto : " + dto);
        memberService.insert(dto);
        redirectAttributes.addFlashAttribute("message",
                "정상적으로 수정되었습니다");
        return "redirect:/member/view";
    }
}
