package hellojpa.entityMapping.identity;

import jakarta.persistence.*;

/**
 * 기본 키 매핑 방법
 *
 * @id 직접 할당하기
 * @GeneratedValue id 생성을 database 에게 위임한것 (자동 생성) Mysql 에 AUTO_INCREMENT 이다
 * 제공하는 옵션
 * IDENTITY : 데이터베이스에 위임, MYSQL
 * IDENTITY 전략 특징. 데이터베이스의 AUTO_INCREMENT 로 pk 값을 생성 해주므로 원래는 영속성 컨텍스트에 저장한후
 * (지연 저장소) 커밋할때 한꺼번에 쿼리를 보내지만, IDENTITY 에서는 pk 값을 모르므로, 영속성 컨텍스트에 중간과정없이
 * 바로 쿼리를 날려준다.
 *
 * SEQUENCE : 데이터베이스 시퀀스 오브젝트 사용,ORACLE DB / @SequenceGenerator 사용
 * TABLE : 키 생성용 테이블 사용,  모든 DB 에서 사용 / @TableGenerator 사용
 * AUTO : 방언에 따라 자동 지정, 기본값
 * 식별자의 조건
 * 기본 키 제약 조건:null 아님, 유일, 변하면 안된다
 * 미래까지 생각해서 만들어야함 (회원 정보 식별자로 주민등록번호? X)
 * 권장 : Long 형 + 대체키 + 키 생성 전략 사용
 */
//@Entity
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
