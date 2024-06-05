package capstone.zigtong.adminserver.domain.resume.controller;

import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.resume.dto.ResumeDto;
import capstone.zigtong.adminserver.domain.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX+"/admins")
public class ResumeController {
    private final ResumeService resumeService;
    @Operation(summary = "workId에 해당하는 멤버의 이력서 조회", description = "관리자가 지원현황페이지에서 특정 workId에 해당하는 멤버의 이력서 조회합니다.")
    @GetMapping("/workers/{workerId}/resumes")
    public ResponseEntity<ResumeDto> getResumeByWorkId(@PathVariable String workerId){
        ResumeDto resumeDto = resumeService.getResume(workerId);
        return ResponseEntity.ok()
                .body(resumeDto);
    }
}
