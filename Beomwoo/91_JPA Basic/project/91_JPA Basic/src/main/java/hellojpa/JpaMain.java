package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //저장
            Team team = new Team();
            team.setName("TeamAA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            //member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            //조회
            Member findMember = em.find(Member.class, member.getId());
            //Long findTeamId = findMember.getTeamId();
            //객체지향적이지 못하다.
            //Team findTeam = em.find(Team.class, findTeamId);
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam.getName());

            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit(); //쿼리가 날라가는 시점
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
