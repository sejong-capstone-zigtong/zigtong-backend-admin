package capstone.zigtong.adminserver.domain.employee;

import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.worker.Worker;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@NoArgsConstructor
public class Employee {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;
    @Column(nullable = false)
    private Workingstatus workingstatus;
    @Column(nullable = false)
    private boolean startAttendanceStatus;
    @Column(nullable = false)
    private boolean endAttendanceStatus;
    @JoinColumn(name="post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @JoinColumn(name="worker_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;


    public Employee(Post post, Worker worker) {
        this.post = post;
        post.addEmployee(this);
        this.worker = worker;
        worker.addEmployee(this);
        workingstatus = Workingstatus.PENDING;
    }
}
