package hellojpa.persistent;

import hellojpa.entityMapping.annotation.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import static hellojpa.Config.emf;

/**
 * 준영속 상태 실습
 */
public class Detached {

    final static EntityManager em = emf.createEntityManager(); //EntityManager 가지고오기

    public static void main(String[] args) {
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            //영속
            Member member = em.find(Member.class, 150L); //한 트랜잭션에서 처음으로 실행할때 쿼리가 나간다
            member.setUserName("AAAAA");

            em.detach(member);//비영속 선언
            //em.clear(); //통으로 영속성 컨텍스트 초기화
            tx.commit(); //커밋할때 db 에 아무런 변화가 없다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
