package capstone.zigtong.adminserver.domain.resume;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeCertificateRelation {
    @EmbeddedId
    private ResumeCertificateRelationId resumeCertificateRelationId;

    @MapsId("workerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", columnDefinition = "char(36)")
    private Resume resume;

    @MapsId("certificateId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id", columnDefinition = "SMALLINT UNSIGNED")
    private Certificate certificate;
}
