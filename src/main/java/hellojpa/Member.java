package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @Entity 는 JPA 가 관리하는 객체이고, 데이터베이스의 table 과 매핑을해서 사용한다
 */
@Entity(name = "MBR")
//@Table(name = "user")//테이블의 이름과 클래스의 이름이 다를수 있다.
public class Member {

    @Id //JPA 에게 primary_Key 가 뭔지를 알려주어야 한다.
    private Long id;
    //@Column(name = "username") //테이블의 속성과 클래스의 속성 이름이 다를수 있다.
    @Column(unique = true,length = 10) //DDL 생성 기능은 DDL 을 자동 생성할 때만 사용되고, JPA 의 실행 로직엔 영향을 주지 않는다.
    private String name;

    public Member() { //Entity 클래스의 생성자는 기본생성자도 가지고 있어야 한다
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }


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
