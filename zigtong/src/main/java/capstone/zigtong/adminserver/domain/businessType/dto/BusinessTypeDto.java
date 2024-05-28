package capstone.zigtong.adminserver.domain.businessType.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessTypeDto {
    private String industryCode;
    private String industryName;
}
