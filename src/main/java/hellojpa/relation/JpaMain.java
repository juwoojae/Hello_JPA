package hellojpa.relation;

import jakarta.persistence.*;

import static hellojpa.Config.emf;

public class JpaMain {
    /**
     * 객체를 테이블에 맞추어서 데이터 중심으로 모델링 하면, 협력 관계를 만들 수 없다.
     * 테이블은 외래 키로 조인을 사용해서 연관된 테이블을 찾는다
     * 객체는 참조를 사용해서 연관된 객체를 찾는다.
     * 테이블과 객체 사이에는 이런 간격이 있다.
     */
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작 Autocommit false
        //code
        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); //member 가 team 을 참조를 하므로
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}

