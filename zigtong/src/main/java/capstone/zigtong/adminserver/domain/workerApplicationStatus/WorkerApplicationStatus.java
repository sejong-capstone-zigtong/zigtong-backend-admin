package capstone.zigtong.adminserver.domain.workerApplicationStatus;

import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.worker.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@NoArgsConstructor
public class WorkerApplicationStatus {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;
    @Column(nullable = false)
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
}
