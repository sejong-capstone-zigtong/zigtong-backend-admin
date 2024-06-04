package capstone.zigtong.adminserver.domain.workerApplicationStatus.dto;

import capstone.zigtong.adminserver.domain.worker.Worker;
import capstone.zigtong.adminserver.domain.worker.dto.WorkerDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.ApplicationStatus;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerApplicationStatusDto {
    private Integer  id;
    private ApplicationStatus applicationStatus;
    private WorkerDto workerDto;

    public static WorkerApplicationStatusDto fromEntity(WorkerApplicationStatus application) {
        Worker worker = application.getWorker();
        WorkerDto dto = WorkerDto.FromEntityForWorkerApplicationStatusDto(worker);
        return new WorkerApplicationStatusDto(
                application.getId(),
                application.getApplicationStatus(),
                dto
        );
    }
}
