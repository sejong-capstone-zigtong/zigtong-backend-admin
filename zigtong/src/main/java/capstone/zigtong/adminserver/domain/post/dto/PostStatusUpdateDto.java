package capstone.zigtong.adminserver.domain.post.dto;

import capstone.zigtong.adminserver.domain.post.RecruitmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostStatusUpdateDto {
    @NotNull
    private RecruitmentStatus recruitmentStatus;
}
