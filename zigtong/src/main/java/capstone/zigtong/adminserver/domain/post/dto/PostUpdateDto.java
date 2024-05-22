package capstone.zigtong.adminserver.domain.post.dto;

import capstone.zigtong.adminserver.domain.post.PostStatus;
import capstone.zigtong.adminserver.domain.post.RecruitmentStatus;
import capstone.zigtong.adminserver.domain.post.WageType;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
public class PostUpdateDto {
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
    public PostDto toDto() {
        return new PostDto(
                content,
                wage,
                address,
                startTime,
                endTime,
                category,
                numberOfApplicants,
                phoneNumber,
                recruitmentStatus,
                wageType,
                recruitmentStartTime,
                recruitmentEndTime,
                lunchTime,
                numberOfRecruits,
                postStatus
        );
    }
}
