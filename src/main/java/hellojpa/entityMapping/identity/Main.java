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
/**
 * @GenerateValue
 * 잘보면 db 에 flush() 가 되어야 db 가 id 를 할당해줄수 있는데 (id 를 할당해주는것을 db 에 위임)
 * flush() 가 안되어도 em.find 로 가지고 올수 있는 경우가 있다(1차 캐시혹은 ,없다면 db 에 쿼리를 날려줌으로서)
 * 이것을 가능하게하는것은 @GenerateValue 의 전략에 의존한다
 * 1. identity
 * 이럴경우 razy sql store 에 있는 insert 쿼리들을 먼저 날려준다
 * 2. sequnce , table
 * persist() 시점에 시퀀스 조회 후 미리 할당.
 */