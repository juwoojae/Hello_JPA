package hellojpa.relation.bothWay;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

import static hellojpa.Config.emf;

/**
 * 양방향 연관관계에서 주의점
 * 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자
 * 연관관계 편의 메서드를 선언하자
 * 양방향 매핑시 무한 루프를 조심하자
 * 예 : toString(), lombok, JSON 라이브러리
 * 그래서 Controller 는 절때 !! Entity 를 반환하면 안된다. DTO 로 반환해야한다.
 */
public class JpaMain {

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작 Autocommit false
        //code
        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);


            Member member = new Member();
            member.setUsername("member1");

            //team.getMembers().add(member);  //양방향 매핑시에는 양쪽에 값을 다 추가해주어야한다
            member.changeTeam(team); //변경 편의 메서드 . member 의 set 과 동시에 team 에 members(컬렉션) 에 member 추가
            //team.addMember(member); team 을 기준으로 편의 메서드를 사용할수도 있다

            em.persist(member);
            //저장

            em.flush();
            em.clear();
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();//이때 team 객체는 members 가 초기화 되어있지 않다, db 에 들어간적이 없어서

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

