package capstone.zigtong.adminserver.domain.resume.dto;

import capstone.zigtong.adminserver.domain.resume.Career;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CareerDto {
    private Integer id;

    private String companyName;
    private String role;
    private String roleDetail;

    private LocalDate startDate;
    private LocalDate endDate;
    public CareerDto(Career career) {
        this.id = career.getId();
        this.companyName = career.getCompanyName();
        this.role = career.getRole();
        this.roleDetail = career.getRoleDetail();
        this.startDate = career.getStartDate();
        this.endDate = career.getEndDate();
    }
}
