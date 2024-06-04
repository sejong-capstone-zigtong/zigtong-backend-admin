package capstone.zigtong.adminserver.domain.skill.repository;

import capstone.zigtong.adminserver.domain.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    @Query("SELECT DISTINCT s.category FROM Skill s")
    List<String> findAllCategories();
}
