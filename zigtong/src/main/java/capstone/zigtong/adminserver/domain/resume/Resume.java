package capstone.zigtong.adminserver.domain.resume;

import capstone.zigtong.adminserver.domain.worker.Worker;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
    @Id
    private String id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;
    private String uploadedUrl;
    private String statement;
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ResumeSkillRelation> resumeSkillRelations = new ArrayList<>();
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ResumeCertificateRelation> resumeCertificateRelations = new ArrayList<>();
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Career> careers;
}
