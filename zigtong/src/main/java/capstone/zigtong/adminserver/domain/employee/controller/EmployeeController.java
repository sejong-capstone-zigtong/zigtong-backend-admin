package capstone.zigtong.adminserver.domain.employee.controller;

import capstone.zigtong.adminserver.domain.employee.dto.EmployeeDto;
import capstone.zigtong.adminserver.domain.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX)
public class EmployeeController {
    private final EmployeeService employeeService;
    @Operation(summary = "근로자 조회", description = "해당 게시글의 근로자들을 조회합니다")
    @GetMapping("/admins/posts/{postId}/employee")
    public ResponseEntity<List<EmployeeDto>> getEmployee(@PathVariable String postId){
        List<EmployeeDto> employeeDtoList = employeeService.getEmployee(Integer.valueOf(postId));
        return ResponseEntity.ok()
                .body(employeeDtoList);
    }
}
