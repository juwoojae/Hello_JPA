package hellojpa.entityMapping.annotation;

import hellojpa.RoleType;
import jakarta.persistence.*;

import java.util.Date;

/**
 * 필드와 컬럼 매핑
 *
 * Hibernate:
 *     create table Member (
 *         age integer,
 *         createdDate timestamp(6),
 *         id bigint not null,
 *         lastModifiedDate timestamp(6),
 *         name varchar(255),
 *         roleType varchar(255) check (roleType in ('USER','ADMIN')),
 *         description clob,
 *         primary key (id)
 *     )
 */
//@Entity
public class Member {
    /// primary Key 매핑
    @Id
    private Long id;

    /**
     * 객체는 userName 이라는 변수를 쓰고싶지만
     * db 의 table 은 이름이 name 이여야 할때 Column 의 name 속성을 이용하여 매핑해준다.
     * nullable 은 우리가 아는 db 의 notNull 제약조건이다
     */
    @Column(name ="name", nullable = false)
    private String userName;

    /**
     * 이렇게 이무런 에노테이션을 지정해주지 않으면 JPA 가 알아서
     * db 의 가장 비슷한 자료형 INT 형으로 row 랑 생성 및 매핑 해준다.
     */
    private Integer age;

    /**
     * db에는 ENUM 타입이 없다 .이럴땐 @Enumerated 를 사용해준다
     * EnumType.ORDINAL : enum 의 순서를 데이터베이스에 저장 (권장되지 않는다)
     * EnumType.STRING : enum 의 이름을 데이터베이스에 저장
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //자바 8 이상에서는 LocalDate 를 지원하므로, 이것을 사용하면 굳이 에노테이션 사용 안해주어도 된다.
    //private LocalDate testLocalDate;
    //private LocalDateTime testLocalDateTime; //TIMESTAMP 형식으로 들어간다

    @Lob
    private String description;

    @Transient//엔티티의 속성으로서만 존재하고 db 에는 공유 하고싶지 않다면
    private String temp;

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
