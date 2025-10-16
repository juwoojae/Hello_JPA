package hellojpa.entityMapping.identity;

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
            member.setUserName("a");
            em.persist(member);
            System.out.println("Members persisted");;
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally{ //하나의 트랜잭션이 끝나면, 무조건 리소스를 반납해주어야한다
            em.close();
        }
        emf.close();
    }
}
