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
    private LocalTime lunchStartTime;
    private LocalTime lunchEndTime;
    private Integer numberOfRecruits;
    private PostStatus postStatus;
    private String title;
    public PostDto toDto() {
        return new PostDto(
                content,
                wage,
                address,
                startTime,
                endTime,
                category,
                phoneNumber,
                wageType,
                recruitmentStartTime,
                recruitmentEndTime,
                lunchStartTime,
                lunchEndTime,
                numberOfRecruits,
                title
        );
    }
}
