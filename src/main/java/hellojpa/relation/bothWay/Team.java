package hellojpa.relation.bothWay;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 연관관계의 주인 (Owner)
 * 양뱡향 매핑 규칙
 * 객체의 두 관계중 하나를 연관관계의 주인으로 지정 (둘 중 하나로 외래 키를 관리해야 한다.)
 * 선택기준 : 외래키가 있는곳, 1:n 일때 n 에해댱하는 객체를, 자동차가 아니라 바퀴를, 선수가 아닌 팀을 연관괸계 주인으로 잡자
 * 연관관계의 주인만이 외래 키를 관리(등록, 수정)
 * 주인이 아닌쪽은 읽기만 가능
 * 주인은 mappedBy 속성 사용 X
 * 주인이 아니면 mappedBy 속성으로 주인지정
 */
@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")//누구랑 매핑되어있는지
    private List<Member> members = new ArrayList<>();

    public Team() {
    }
    public void addMember(Member member) {
        //member.setTeam(this)
        members.add(member);
    }
    public Team(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}
