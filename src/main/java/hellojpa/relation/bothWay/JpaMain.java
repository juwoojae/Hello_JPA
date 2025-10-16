package hellojpa.relation.bothWay;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

import static hellojpa.Config.emf;

public class JpaMain {
    /**
     */
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작 Autocommit false
        //code
        try {
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); //member 가 team 을 참조를 하므로
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

