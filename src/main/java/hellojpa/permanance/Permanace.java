package hellojpa.permanance;

import hellojpa.Config;
import hellojpa.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import static hellojpa.Config.*;

/**
 * 영속성 컨텍스트를 제공하는 이유
 * 1. 1차 캐시
 * 2. 동일성(identity) 보장
 * 3. 트랜잭션을 지원하는 쓰기 지연 (transactional write-behind)
 * 4. 변경 감지 (Dirty Checking)
 * 5. 지연 로딩 (Lazy Loading)
 */
public class Permanace {

    final static EntityManager em = emf.createEntityManager(); //EntityManager 가지고오기

    public static void main(String[] args) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            //비영속
            Member member = new Member();
            member.setId(3L);
            member.setName("JPA");

            //영속
            System.out.println("===BEFORE===");
            em.persist(member); //이때 바로 db 에 저장되는것(쿼리에 날리는것) 이 아님,영속성 컨텍스트에만 저장이 된것
            //em.detach(member); 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            System.out.println("===AFTER===");
            tx.commit(); //커밋을 해야지만 dp 에 쿼리가 날라가게 된다.
        }catch(Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
