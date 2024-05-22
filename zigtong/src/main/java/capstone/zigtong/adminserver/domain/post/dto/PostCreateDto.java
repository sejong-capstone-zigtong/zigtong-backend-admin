package capstone.zigtong.adminserver.domain.post.dto;

import capstone.zigtong.adminserver.domain.post.PostStatus;
import capstone.zigtong.adminserver.domain.post.RecruitmentStatus;
import capstone.zigtong.adminserver.domain.post.WageType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
public class PostCreateDto {
    @NotBlank
    private String content;
    @NotBlank
    private BigInteger wage;
    @NotBlank
    private String address;
    @NotBlank
    private LocalDateTime startTime;
    @NotBlank
    private LocalDateTime endTime;
    @NotBlank
    private String category;
    @NotBlank
    private Integer numberOfApplicants;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private RecruitmentStatus recruitmentStatus;
    @NotBlank
    private WageType wageType;
    @NotBlank
    private LocalDateTime recruitmentStartTime;
    @NotBlank
    private LocalDateTime recruitmentEndTime;
    @NotBlank
    private LocalTime lunchTime;
    @NotBlank
    private Integer numberOfRecruits;
    @NotBlank
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
