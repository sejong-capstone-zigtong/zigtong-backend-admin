package capstone.zigtong.adminserver.domain.skill.dto;

import capstone.zigtong.adminserver.domain.skill.Skill;
import lombok.Data;

@Data
public class SkillDto {
    Integer id;
    String category;
    String name;

    public SkillDto(Skill skill) {
        id = skill.getId();
        category = skill.getCategory();
        name = skill.getName();
    }
}
