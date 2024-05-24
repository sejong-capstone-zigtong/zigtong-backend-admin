package capstone.zigtong.adminserver.domain.worker.repository;

import capstone.zigtong.adminserver.domain.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, String> {
}
