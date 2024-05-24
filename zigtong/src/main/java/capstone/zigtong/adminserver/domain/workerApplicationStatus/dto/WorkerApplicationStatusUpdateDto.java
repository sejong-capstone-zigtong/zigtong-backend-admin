package capstone.zigtong.adminserver.domain.workerApplicationStatus.dto;

import capstone.zigtong.adminserver.domain.workerApplicationStatus.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class WorkerApplicationStatusUpdateDto {
    @NotBlank
    private ApplicationStatus applicationStatus;
}
