package capstone.zigtong.adminserver.domain.skill.dto;

import capstone.zigtong.adminserver.domain.skill.Skill;

public record SkillCategoryDto(String category) {
    public static SkillCategoryDto from(Skill skill) {
        return new SkillCategoryDto(skill.getCategory());
    }
}
