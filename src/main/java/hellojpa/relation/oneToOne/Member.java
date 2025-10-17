package hellojpa.relation.oneToOne;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private int id;

    @Column(name ="USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID",unique = true) //다대일 양방향 매핑처럼 외래키가 있는곳이 연관관계의 주인
    private Locker locker;

    public Member() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Locker getLocker() {
        return locker;
    }

    //편의 메서드
    public void affectLocker(Locker locker) {
        this.locker = locker;
        locker.setMember(this);
    }
}
