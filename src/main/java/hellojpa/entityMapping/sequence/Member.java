package hellojpa.entityMapping.sequence;

import jakarta.persistence.*;

/**
 * sequence 전략 특징
 * 데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트 (oracle 시퀀스)
 */
//@Entity
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR"
        , sequenceName = "MEMBER_SEQ"
        , initialValue = 1, allocationSize = 50
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String userName;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
