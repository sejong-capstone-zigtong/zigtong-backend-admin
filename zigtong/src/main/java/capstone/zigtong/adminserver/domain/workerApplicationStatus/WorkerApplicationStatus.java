package capstone.zigtong.adminserver.domain.workerApplicationStatus;

import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.worker.Worker;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusUpdateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WorkerApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;
    @Column(nullable = false, name = "application_status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
    @JoinColumn(name="post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @JoinColumn(name="worker_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;
    public WorkerApplicationStatus(Post post, Worker worker) {
        this.applicationStatus = ApplicationStatus.DEFAULT;
        this.post = post;
        post.addWorkerApplicationStatus(this);
        this.worker = worker;
        worker.addWorkerApplicationStatus(this);
    }

    public void updateByDto(WorkerApplicationStatusUpdateDto requestDto) {
        this.applicationStatus = requestDto.getApplicationStatus();
    }
}
