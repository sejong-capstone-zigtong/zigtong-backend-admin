package capstone.zigtong.adminserver.domain.skill;

import capstone.zigtong.adminserver.domain.relation.ResumeSkillRelation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;

    private String category;

    private String name;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResumeSkillRelation> resumeSkillRelation;
}
