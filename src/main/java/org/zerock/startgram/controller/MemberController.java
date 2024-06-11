package org.zerock.startgram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.startgram.DTO.MemberJoinDTO;
import org.zerock.startgram.service.MemberService;

import java.security.Principal;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/join")
    public void joinGET(){
        log.info("join get...............");
    }
    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
        log.info("join post...............");
        log.info(memberJoinDTO);
        try{
            //회원가입 서비스 실행
            memberService.join(memberJoinDTO);
            //아이디가 존재 할 경우 에러 발생
        }catch(MemberService.MidExistException e){
            //에러 발생시 리다이렉트 페이지에 error=mid 값을 가지고 이동
            redirectAttributes.addFlashAttribute("error","mid");
            return "redirect:/member/join";
        }
        redirectAttributes.addFlashAttribute("result","success");
        return "redirect:/board/join";
//        return "redirect:/board/list";
    }
}