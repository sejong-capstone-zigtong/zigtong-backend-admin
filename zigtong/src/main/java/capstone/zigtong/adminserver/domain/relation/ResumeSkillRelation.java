package capstone.zigtong.adminserver.domain.relation;

import capstone.zigtong.adminserver.domain.resume.Resume;
import capstone.zigtong.adminserver.domain.skill.Skill;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeSkillRelation {
    @EmbeddedId
    private ResumeSkillRelationId resumeSkillRelationId;

    @MapsId("workerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", columnDefinition = "char(36)")
    private Resume resume;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", columnDefinition = "SMALLINT UNSIGNED")
    private Skill skill;

    public static ResumeSkillRelation create(Resume resume, Skill skill) {
        ResumeSkillRelation resumeSkillRelation = new ResumeSkillRelation();

        resumeSkillRelation.resumeSkillRelationId = new ResumeSkillRelationId();
        resumeSkillRelation.resumeSkillRelationId.workerId = resume.getId();
        resumeSkillRelation.resumeSkillRelationId.skillId = skill.getId();

        resumeSkillRelation.resume = resume;
        resumeSkillRelation.skill = skill;

        return resumeSkillRelation;
    }

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class ResumeSkillRelationId implements Serializable {
        private String workerId;
        private Integer skillId;
    }
}
