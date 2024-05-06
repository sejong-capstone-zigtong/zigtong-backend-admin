package capstone.zigtong.adminserver.domain.post;

import capstone.zigtong.adminserver.domain.admin.Admin;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;
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
    private Integer numberOfApplicants;
    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false)
    private RecruitmentStatus recruitmentStatus;
    @Column(nullable = false)
    private WageType wageType;
    @Column(nullable = false)
    private LocalDateTime recruitmentStartTime;
    @Column(nullable = false)
    private LocalDateTime recruitmentEndTime;
    @Column(nullable = false)
    private LocalTime lunchTime;
    @Column(nullable = false)
    private Integer numberOfRecruits;
    @Column(nullable = false)
    private PostStatus postStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private Admin admin;








}
