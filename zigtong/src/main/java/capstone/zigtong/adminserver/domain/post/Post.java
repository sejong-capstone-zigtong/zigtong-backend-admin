package capstone.zigtong.adminserver.domain.post;

import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.base.BaseTimeEntity;
import capstone.zigtong.adminserver.domain.employee.Employee;
import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private BigInteger wage;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Integer numberOfApplicants = 0;
    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false)
    private RecruitmentStatus recruitmentStatus = RecruitmentStatus.RECRUITING;
    @Column(nullable = false)
    private WageType wageType;
    @Column(nullable = false)
    private LocalDateTime recruitmentStartTime;
    @Column(nullable = false)
    private LocalDateTime recruitmentEndTime;
    @Column(nullable = false)
    private LocalTime lunchStartTime;
    @Column(nullable = false)
    private LocalTime lunchEndTime;
    @Column(nullable = false)
    private Integer numberOfRecruits;
    @Column(nullable = false)
    private PostStatus postStatus = PostStatus.PROCEEDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private Admin admin;
    @OneToMany(mappedBy = "post")
    private List<Employee> employeeList = new ArrayList<>();
    @OneToMany(mappedBy = "post")
    private List<WorkerApplicationStatus> workerApplicationStatusList = new ArrayList<>();



    public Post(Admin admin, PostDto postDto) {
        this.admin = admin;
        admin.addPostList(this);
        this.content = postDto.getContent();
        this.wage = postDto.getWage();
        this.address = postDto.getAddress();
        this.startTime = postDto.getStartTime();
        this.endTime = postDto.getEndTime();
        this.category = postDto.getCategory();
        this.phoneNumber = postDto.getPhoneNumber();
        this.wageType = postDto.getWageType();
        this.recruitmentStartTime = postDto.getRecruitmentStartTime();
        this.recruitmentEndTime = postDto.getRecruitmentEndTime();
        this.lunchStartTime = postDto.getLunchStartTime();
        this.lunchEndTime = postDto.getLunchEndTime();
        this.numberOfRecruits = postDto.getNumberOfRecruits();
        this.title = postDto.getTitle();
    }

    public void updateByDto(PostDto postDto) {
        this.content = postDto.getContent();
        this.wage = postDto.getWage();
        this.address = postDto.getAddress();
        this.startTime = postDto.getStartTime();
        this.endTime = postDto.getEndTime();
        this.category = postDto.getCategory();
        this.numberOfApplicants = postDto.getNumberOfApplicants();
        this.phoneNumber = postDto.getPhoneNumber();
        this.recruitmentStatus = postDto.getRecruitmentStatus();
        this.wageType = postDto.getWageType();
        this.recruitmentStartTime = postDto.getRecruitmentStartTime();
        this.recruitmentEndTime = postDto.getRecruitmentEndTime();
        this.lunchStartTime = postDto.getLunchStartTime();
        this.lunchEndTime = postDto.getLunchEndTime();
        this.numberOfRecruits = postDto.getNumberOfRecruits();
        this.postStatus = postDto.getPostStatus();
        this.title = postDto.getTitle();
    }

    public void addWorkerApplicationStatus(WorkerApplicationStatus workerApplicationStatus) {
        workerApplicationStatusList.add(workerApplicationStatus);
        numberOfApplicants++;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
