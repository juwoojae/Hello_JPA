package hellojpa.entityMapping.sequence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import static hellojpa.Config.emf;

public class Main {
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작 Autocommit false
        try{
            Member member = new Member();
            //member.setId("ID_A");
            member.setUserName("d");
            System.out.println("==================");
            em.persist(member);
            System.out.println("==================");
            Member member1 = new Member();
            //member.setId("ID_A");
            member1.setUserName("D");
            System.out.println("==================");
            em.persist(member1);  //이때는 allocationSize = 50 로 이미 메모리가 할당되어져 있어서 쿼리를 던지지 않는다!! 개꿀
            System.out.println("==================");
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally{ //하나의 트랜잭션이 끝나면, 무조건 리소스를 반납해주어야한다
            em.close();
        }
        emf.close();
    }
}
