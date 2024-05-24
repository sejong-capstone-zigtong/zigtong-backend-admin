package capstone.zigtong.adminserver.domain.workerApplicationStatus.repository;

import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerApplicationStatusRepository extends JpaRepository<WorkerApplicationStatus, String> {
}
