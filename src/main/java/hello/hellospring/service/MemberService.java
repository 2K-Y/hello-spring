package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional //데이터를 변경하거나  저장할때는 이게 있어야 됨
//@Service //비즈니스 로직을 만들고
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }
    // 테스트 케이스의 레포지토리랑 여기 레포지 토리가 다름 여기서는 static이라서 문제 없지만 db가 다르면 문제가 됨
    // 같은걸로 테스트하는게 맞음
    /**
     *
     * 회원가입
     */
    public Long join(Member member) {

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();

        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join =" + timeMs + "ms");

    }

//        //중복 회원은 안됨
//        validateDuplicateMember(member);
//
//        memberRepository.save(member);
//        return member.getId();//회원가입을하면 아이디만 반환
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{ //값이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");// throw new = 예외발생
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
