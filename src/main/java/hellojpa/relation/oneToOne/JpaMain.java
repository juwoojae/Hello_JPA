package hellojpa.relation.oneToOne;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

import static hellojpa.Config.emf;

public class JpaMain {
    /**
     * 일대일 관계는 그 반대도 일대일
     * 외래 키에 데이터베이스 유니크(UNI) 제약조건 추가 (1대 1이니깐 당연함)
     * 주 테이블이나 대상 테이블 중에 외래 키 선택 가능
     * 외래 키 선택에서의 trade off
     * 1. 주 테이블에 외래 키
     *  객체지향적으로 봤을때 좋은설계이다 주객체가 대상의 객체를 참조하고 있는 형태
     *  JPA 매핑이 편리하다
     * 2. 대상 테이블에 외래 키
     *  db 설계에 있어서 좋은 설계이다
     *  주테이블과 대상테이블을 일대일에서
     */
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작 Autocommit false
        //code
        try {
            Locker locker = new Locker();
            locker.setPassword("password");
            em.persist(locker);

            Member member = new Member();
            member.setUsername("test");
            member.affectLocker(locker);
            em.persist(member); //영속성 컨텍스트올리기

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
