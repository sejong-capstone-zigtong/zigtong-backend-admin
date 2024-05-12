package capstone.zigtong.adminserver.domain.post.dto;

import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.post.PostStatus;
import capstone.zigtong.adminserver.domain.post.RecruitmentStatus;
import capstone.zigtong.adminserver.domain.post.WageType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
@AllArgsConstructor
public class PostDto {
    private String id;
    private String content;
    private BigInteger wage;
    private String address;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String category;
    private Integer numberOfApplicants;
    private String phoneNumber;

    private RecruitmentStatus recruitmentStatus;
    private WageType wageType;
    private LocalDateTime recruitmentStartTime;
    private LocalDateTime recruitmentEndTime;
    private LocalTime lunchTime;
    private Integer numberOfRecruits;
    private PostStatus postStatus;

    public PostDto(String content, BigInteger wage, String address, LocalDateTime startTime, LocalDateTime endTime, String category, Integer numberOfApplicants, String phoneNumber, RecruitmentStatus recruitmentStatus, WageType wageType, LocalDateTime recruitmentStartTime, LocalDateTime recruitmentEndTime, LocalTime lunchTime, Integer numberOfRecruits, PostStatus postStatus) {

        this.content = content;
        this.wage = wage;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.numberOfApplicants = numberOfApplicants;
        this.phoneNumber = phoneNumber;
        this.recruitmentStatus = recruitmentStatus;
        this.wageType = wageType;
        this.recruitmentStartTime = recruitmentStartTime;
        this.recruitmentEndTime = recruitmentEndTime;
        this.lunchTime = lunchTime;
        this.numberOfRecruits = numberOfRecruits;
        this.postStatus = postStatus;
    }

    public static PostDto fromEntity(Post post) {
        return new PostDto(
                post.getId(),
                post.getContent(),
                post.getWage(),
                post.getAddress(),
                post.getStartTime(),
                post.getEndTime(),
                post.getCategory(),
                post.getNumberOfApplicants(),
                post.getPhoneNumber(),
                post.getRecruitmentStatus(),
                post.getWageType(),
                post.getRecruitmentStartTime(),
                post.getRecruitmentEndTime(),
                post.getLunchTime(),
                post.getNumberOfRecruits(),
                post.getPostStatus()
        );
    }
}
