package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    //회원 가입을 위한 서비스
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;



    // 테스트 케이스의 레포지토리랑 여기 레포지 토리가 다름 여기서는 static이라서 문제 없지만 db가 다르면 문제가 됨
    // 같은걸로 테스트하는게 맞음


    @Test//테스트코드는 한글로 적어도 괜찮음
    void 회원가입() {
        //given 주어짐
        Member member = new Member();
        member.setName("spring");

        //when 실행했을 때
        Long saveId = memberService.join(member);
        //저장을 한게 포지토리에 있냐

        //then 이런 결과
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }



}