package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller //외부 요청을 받고 + 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
public class MemberController {

    private final MemberService memberService;

    @Autowired//이게 있으면 멤버 서비스를 가져다가 연결을 시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")//get 방식
    public String createForm(){
        return "members/createMemberForm"; // 얘가 리턴하면 template에서 찾음
    }

    @PostMapping("/members/new")// data 등록 get 조회 post
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member =" + member.getName());

        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    //
}
