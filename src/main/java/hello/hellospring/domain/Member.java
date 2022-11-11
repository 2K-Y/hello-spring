package hello.hellospring.domain;

import javax.persistence.*;

@Entity
//jpa는 인터페이스고 hibernate가 구현해주는거임
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id; // 요구사항에서 id랑 name이 필요했음

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


