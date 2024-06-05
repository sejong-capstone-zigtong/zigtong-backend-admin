package capstone.zigtong.adminserver.domain.businessType.controller;

import capstone.zigtong.adminserver.domain.admin.dto.AdminDto;
import capstone.zigtong.adminserver.domain.businessType.dto.BusinessTypeDto;
import capstone.zigtong.adminserver.domain.businessType.service.BusinessTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX+"/admins")
@CrossOrigin(origins = "*")
public class BusinessTypeController {
    private final BusinessTypeService businessTypeService;
    @Operation(summary = "업종 조회", description = "회원가입 시 선택할 업종을 조회합니다")
    @GetMapping("/business-type")
    public ResponseEntity<List<BusinessTypeDto>> getBusinessType(){
        List<BusinessTypeDto> businessTypeDtoList = businessTypeService.getBusinessType();
        return ResponseEntity.ok()
                .body(businessTypeDtoList);
    }
}
