package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); //회원 저장소 저장
    Optional<Member> findById(Long id); //id 로 회원을 찾음  Optional Null반환 대신에 저걸로 받음
    Optional<Member> findByName(String name);
    List<Member> findAll(); //얘는 저장된 모든 회원반환

}
