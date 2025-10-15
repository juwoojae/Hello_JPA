package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작 Autocommit false
        //code
        try{
//            create
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);
            /**
             * update 이거 ㄹㅇ 미친놈임
             * 아니, 가지고 온 Member객체의 속성을 setter 로 바꿨는데 어찌 db 값이 변경되는거지 ? 기똥차네
             * 그이유는 JPA 가 반환한 Entity 는 JPA 가 관리를 한다, 그리고 JPA 가 변경내역을 트랜잭션이 commit 하는 시점에
             * update Query 를 날린다.
             */
//            Member findMember = em.find(Member.class, 1L); //jpa 조회
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
//            findMember.setName("HelloJPA"); //변경내역이 생긴다
            //JPQL
            // 애플리케이션이 필요한 데이터만 DB 에서 불러오려면 결국 검색 조건이 포함된 SQL 이 필요하다!!
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList(); //table 대상이아닌, Member Entity 를 대상으로 가지고 온다
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally{ //하나의 트랜잭션이 끝나면, 무조건 리소스를 반납해주어야한다
            em.close();
        }

        tx.commit();

        em.close();
        emf.close();
    }
}
