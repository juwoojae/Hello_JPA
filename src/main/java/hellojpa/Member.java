package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
//@Table(name = "user")//테이블의 이름과 클래스의 이름이 다를수 있다.
public class Member {

    @Id //JPA 에게 primary_Key 가 뭔지를 알려주어야 한다.
    private Long id;
    //@Column(name = "username") //테이블의 속성과 클래스의 속성 이름이 다를수 있다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
