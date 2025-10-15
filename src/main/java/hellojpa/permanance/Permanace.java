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
        /**
         * 1차 캐시
         *
         */
        tx.begin();
        try {

            //비영속
            Member member = new Member();
            member.setId(4L);
            member.setName("JPA");

            //영속
            System.out.println("===BEFORE===");
            em.persist(member); //이때 바로 db 에 저장되는것(쿼리에 날리는것) 이 아님,영속성 컨텍스트에만 저장이 된것
            System.out.println("===AFTER===");

            Member findMember = em.find(Member.class, 4L);//select 쿼리를 안던진다. 영속성 컨텍스트에 저장되어있는 1차캐시에서 가져오기 때문이다.

            System.out.println("findMember.getId = " + findMember.getId());
            System.out.println("findMember.getName = " + findMember.getName());

            tx.commit(); //커밋을 해야지만 dp 에 쿼리가 날라가게 된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        /**
         * 2. 동일성(identity) 보장
         * 첫번째 조회를 할때는 쿼리를 날려준후 영속성 컨텍스트(1차 캐시에 저장한다)
         * 그리고 두번째 조회를 할때는 dp로 쿼리를 다시 날리는것이아닌 1차 캐시에서 값을 가지고 온다
         */
        tx.begin();
        try {
            //영속
            Member findMember1 = em.find(Member.class, 4L);
            Member findMember2 = em.find(Member.class, 4L);

            System.out.println(findMember1==findMember2); //영속 엔티티의 동일성 보장

            tx.commit(); //커밋을 해야지만 dp 에 쿼리가 날라가게 된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
        /**
         * 트랜잭션을 지원하는 쓰기 지연 (transactional write-behind)
         * 커밋하기 직전까지 영속성 컨텍스트에 모으기
         * 그와 동시에 SQL 쿼리 저장소에 저장해서 (쓰기지연)이 일어나면서 트랜잭션 커밋이 발생하면
         * 쿼리를 한번에 db에 보낸다
         */
        tx.begin();
        try {
           Member member1 = new Member(150L, "A");
           Member member2 = new Member(160L, "B");

           em.persist(member1);
           em.persist(member2); //영속성 컨텍스트에 쌓임과 동시에 SQL 도 누적되서 쌓인다
            System.out.println("==============");

            tx.commit(); //커밋을 해야지만 dp 에 쿼리가 날라가게 된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        /**
         * 변경 감지
         *  처음 영속성 컨텍스트에 저장을할때 스냅샷을 떠서 저장해두고,
         *  커밋을 할시점에 현재 엔티티와 스냅샷을 비교하고 변경내역이 있다면
         *  updatesql 을 생성해서 쓰기 지연 저장소에 넣어두고 ,그후 이 쿼리로
         *  트랜잭션 커밋을해서 db 로 넘어간다
         */
        tx.begin();
        try {
            Member member = em.find(Member.class, 1L);
            member.setName("ZZZZZ");
            //em.update(member) 이런 코드가 있어야하지 않을까?
            tx.commit(); //커밋을 해야지만 dp 에 쿼리가 날라가게 된다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }


}
