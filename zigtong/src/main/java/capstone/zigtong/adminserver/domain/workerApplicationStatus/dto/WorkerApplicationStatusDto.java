package capstone.zigtong.adminserver.domain.workerApplicationStatus.dto;

import capstone.zigtong.adminserver.domain.worker.Worker;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.ApplicationStatus;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerApplicationStatusDto {
    private String  id;
    private ApplicationStatus applicationStatus;
    private Worker worker;
    public static WorkerApplicationStatusDto fromEntity(WorkerApplicationStatus application) {
        return new WorkerApplicationStatusDto(
                application.getId(),
                application.getApplicationStatus(),
                application.getWorker()
        );
    }
}
