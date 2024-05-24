package capstone.zigtong.adminserver.domain.workerApplicationStatus.dto;

import capstone.zigtong.adminserver.domain.workerApplicationStatus.ApplicationStatus;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkerApplicationStatusDto {
    private String  id;

    public static WorkerApplicationStatusDto fromEntity(WorkerApplicationStatus application) {
        return new WorkerApplicationStatusDto(
                application.getId()
        );
    }
}
