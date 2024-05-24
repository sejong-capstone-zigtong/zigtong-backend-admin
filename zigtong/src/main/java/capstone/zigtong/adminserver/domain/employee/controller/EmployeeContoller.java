package capstone.zigtong.adminserver.domain.employee.controller;

import capstone.zigtong.adminserver.domain.employee.Employee;
import capstone.zigtong.adminserver.domain.employee.dto.EmployeeCreateDto;
import capstone.zigtong.adminserver.domain.employee.dto.EmployeeDto;
import capstone.zigtong.adminserver.domain.employee.service.EmployeeService;
import capstone.zigtong.adminserver.global.exception.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX+"/admins")
public class EmployeeContoller {
    private final EmployeeService employeeService;
    @Operation(summary = "근로자 추가", description = "근로자를 추가합니다")
    @PostMapping("/{adminId}/posts/{postId}/employee")
    public ResponseEntity<CommonResponse> createEmployee(@PathVariable String adminId, @PathVariable String postId,
                                                         @RequestBody EmployeeCreateDto employeeCreateDto){
        EmployeeDto employeeDto = employeeService.createEmployee(adminId, postId, employeeCreateDto.toDto());
    }
}
