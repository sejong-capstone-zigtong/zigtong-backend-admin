package capstone.zigtong.adminserver.domain.workerApplicationStatus.controller;

import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusUpdateDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.service.WorkerApplicationStatusService;
import capstone.zigtong.adminserver.global.exception.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX + "/admins/{adminId}/posts/{postId}")
public class WorkerApplicationStatusController {
    private final WorkerApplicationStatusService workerApplicationStatusService;
    @Operation(summary = "구직자 지원현황 생성", description = "구직자 지원현황 대한 데이터를 생성합니다(생성시 status: DEFAULT 값 넣었음")
    @PostMapping("/workers/{workerId}worker-application-status")
    public ResponseEntity<CommonResponse> createApplication(@PathVariable String adminId, @PathVariable String postId, @PathVariable String workerId){
        WorkerApplicationStatusDto workerApplicationStatusDto = workerApplicationStatusService.createApplication(postId, workerId);
        return ResponseEntity.ok()
                .body(new CommonResponse(workerApplicationStatusDto.getId(), "successfully created"));
    }
    @Operation(summary = "구직자 지원 수락/거절", description = "해당 게시글의 지원자를 수락할지 거절할지 선택합니다")
    @PostMapping("/worker-application-status/{workerApplicationId}")
    public ResponseEntity<CommonResponse> updateApplication(@PathVariable String adminId, @PathVariable String postId,@PathVariable String workerApplicationId, @RequestBody WorkerApplicationStatusUpdateDto requestDto){
        WorkerApplicationStatusDto workerApplicationStatusDto = workerApplicationStatusService.updateApplication(postId, workerApplicationId, requestDto);
        return ResponseEntity.ok()
                .body(new CommonResponse(workerApplicationStatusDto.getId(), "successfully updated"));
    }
    @Operation(summary = "지원자들 조회", description = "해당 게시글에 지원한 유저들을 모두 조회합니다")
    @GetMapping("/worker-application-status")
    public ResponseEntity<List<WorkerApplicationStatusDto>> getWorkerApplications(@PathVariable String adminId, @PathVariable String postId){
        List<WorkerApplicationStatusDto> result = workerApplicationStatusService.getApplications(postId);
        return ResponseEntity.ok()
                .body(result);
    }
}
