package capstone.zigtong.adminserver.domain.certificate;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Certificate {
    @Id
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CertificateType type;

    private String affiliation;

    private String item;
}
