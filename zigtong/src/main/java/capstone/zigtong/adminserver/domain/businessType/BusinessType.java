package capstone.zigtong.adminserver.domain.businessType;

import capstone.zigtong.adminserver.domain.workerApplicationStatus.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BusinessType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;
    @Column(nullable = false)
    private String industryCode;
    @Column(nullable = false)
    private String industryName;
}
