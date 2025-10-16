package hellojpa.flush;

import hellojpa.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import static hellojpa.Config.emf;

public class Flush {
    final static EntityManager em = emf.createEntityManager(); //EntityManager 가지고오기

    /**
     * 사실 트랜잭션을 커밋을해야 영속성 컨텍스트의 쓰기지연 SQL 저장소에 있던 쿼리가 나가는것이 아니고,
     * flush 라는 과정을 통해서 영속성 컨택스트의 변경 내용을 데이터베이스에 반영한다.
     * flush 가 1차 캐시를 비워주는것이 아니다!!
     *
     * JPQL 쿼리 실행시 플러시가 곧바로 자동으로 실행된다.
     * 플러시 모드 옵션
     *
     * FlushModeType.Auto
     * 커밋이나 쿼리를 실행할 때 플러시(기본값)
     * FlushModeType.Commit
     * 커밋할 때만 플러시
     */
    public static void main(String[] args) {
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작
        try {
            //비영속
            Member member = new Member(200L, "member200");
            em.persist(member);
            em.flush(); //쿼리가 먼저 나간다

            System.out.println("==============");
            tx.commit(); //커밋을 해야지만 dp 에 쿼리가 날라가게 된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}