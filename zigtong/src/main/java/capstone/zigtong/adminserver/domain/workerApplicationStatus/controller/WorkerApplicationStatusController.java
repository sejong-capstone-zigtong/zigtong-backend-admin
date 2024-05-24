package capstone.zigtong.adminserver.domain.workerApplicationStatus.controller;

import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.service.WorkerApplicationStatusService;
import capstone.zigtong.adminserver.global.exception.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX + "/admins")
public class WorkerApplicationStatusController {
    private final WorkerApplicationStatusService workerApplicationStatusService;
    @Operation(summary = "유저지원 생성", description = "유저의 지원에 대한 데이터를 생성합니다")
    @PostMapping("/posts/{postId}/workers/{workerId}worker-application-status")
    public ResponseEntity<CommonResponse> createApplication(@PathVariable String postId, @PathVariable String workerId ){
        WorkerApplicationStatusDto workerApplicationStatusDto = workerApplicationStatusService.createApplication(postId, workerId);
        return ResponseEntity.ok()
                .body(new CommonResponse(workerApplicationStatusDto.getId(), "successfully created"));
    }
}
