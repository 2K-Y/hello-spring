package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.source.ConfigurationPropertyCaching;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach// 주의점 1 순서에 의존적으로 만들면 안됨 그래서 이걸 실행하면 테스트케이스(3개)를 실행하면 자기들 맘대로 실행됨
    //그래서 오류가 뜨니까 각 메서드가 실행되면 aftereach로 올 수 있도록 생성
    public void afterEach(){
        repository.clearStore();
    }
    @Test // 얘는 save 기능 테스트
    public void save() {
        Member member = new Member();
        member.setName("spring"); //이름을 setting spring으로 했음

        repository.save(member); // repository 에 저장

        Member result = repository.findById(member.getId()).get();// get으로 꺼냄
        Assertions.assertThat(member).isEqualTo(result);//
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6 rename
        member2.setName("spring2");
        repository.save(member2);

       Member result  = repository.findByName("spring1").get();

       assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
