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

    @GetMapping("/login")
    public void loginGET(String error, String logout){
        log.info("login get...............");
        log.info("logout: "+logout);
        if(logout != null){
            log.info("user logout...........");
        }
    }

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
        return "redirect:/board/list";
    }
    @GetMapping("/modify")
    //Principal : 로그인 정보가 모두 담겨있는 객체 (아이디, 권한)
    public void modifyGET(Principal principal, Model model){
        log.info("modify get...............");
        model.addAttribute("member",memberService.getMember(principal.getName()));
    }
    @PostMapping("/modify")
    public String modifyPOST(MemberJoinDTO joinDTO){
        log.info("modify POST...............");
        memberService.modify(joinDTO);
        return "redirect:/board/list";
    }
    @PostMapping("/remove")
    public String removePOST(String mid){
        memberService.remove(mid);
        // 계정 삭제 후 로그아웃, 로그아웃 하지않으면 삭제된 계정이 로그인 상태로 남게됨
        return "redirect:/logout";
    }
}