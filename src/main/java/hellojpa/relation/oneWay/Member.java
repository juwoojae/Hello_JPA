package hellojpa.relation.oneWay;

import jakarta.persistence.*;

/**
 * 객체를 테이블에 맞추어서 데이터 중심으로 모델링 하면, 협력 관계를 만들 수 없다.
 * 테이블은 외래 키로 조인을 사용해서 연관된 테이블을 찾는다
 * 객체는 참조를 사용해서 연관된 객체를 찾는다.
 * 테이블과 객체 사이에는 이런 간격이 있다.
 */
//@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private int id;

    @Column(name ="USERNAME")
    private String username;

    //    @Column(name = "TEAM_ID")
    //    private Long teamId;

    @ManyToOne //N to 1
    @JoinColumn(name = "TEAM_ID") //실제로 조인하는 컬럼이 뭔지
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
