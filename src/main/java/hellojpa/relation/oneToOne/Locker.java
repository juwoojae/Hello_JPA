package hellojpa.relation.oneToOne;

import jakarta.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    private  long id;
    private String password;

    @OneToOne(mappedBy = "locker")
    private Member member;

    public Locker() {
    }

    public long getId() {

        return id;
    }

    public String getPassword() {
        return password;
    }

    public Member getMember() {
        return member;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
