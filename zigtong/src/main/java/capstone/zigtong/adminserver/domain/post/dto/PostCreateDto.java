package capstone.zigtong.adminserver.domain.post.dto;

import capstone.zigtong.adminserver.domain.post.PostStatus;
import capstone.zigtong.adminserver.domain.post.RecruitmentStatus;
import capstone.zigtong.adminserver.domain.post.WageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
public class PostCreateDto {

    @NotNull
    private String content;
    @NotNull
    private BigInteger wage;
    @NotNull
    private String address;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private String category;
    //@NotBlank
    //private Integer numberOfApplicants;
    @NotNull
    private String phoneNumber;
    //@NotBlank
    //private RecruitmentStatus recruitmentStatus;
    @NotNull
    private WageType wageType;
    @NotNull
    private LocalDateTime recruitmentStartTime;
    @NotNull

    private LocalDateTime recruitmentEndTime;
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lunchStartTime;
    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lunchEndTime;
    @NotNull
    private Integer numberOfRecruits;
    //@NotBlank
    //private PostStatus postStatus;
    @NotNull
    private String title;

    public PostDto toDto() {
        return new PostDto(
                content,
                wage,
                address,
                startTime,
                endTime,
                category,
                //numberOfApplicants,
                phoneNumber,
                //recruitmentStatus,
                wageType,
                recruitmentStartTime,
                recruitmentEndTime,
                lunchStartTime,
                lunchEndTime,
                numberOfRecruits,
                //postStatus
                title
        );
    }
}
