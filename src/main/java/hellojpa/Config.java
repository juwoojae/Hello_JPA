package hellojpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * JPA 표준 API를 순수하게 사용할 때 필요한 코드
 * Persistence 클래스가 제공되는데, persistence.xml 에서 제공하는 설정 정보를 조회해서 ,
 * EntityManagerFactory 객체를 생성한다. EntityManagerFactory 는 애플리케이션에서 실행할때 딱 한번만 생성해서 공유한다
 *
 * 이객체를 이용해서 EntityManager 를 필요할때마다 생성 가능하다.
 * 쓰레드간에 공유 X (사용하고 버려야한다)
 * EntityManager 는 마치 자바의 Collection프레임 웤 처럼 동작한다고 이해하면 쉽게 이해 된다
 *
 * JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행(조회는 ㄴ)
 */
public class Config {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
}
