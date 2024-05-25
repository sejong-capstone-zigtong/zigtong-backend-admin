package capstone.zigtong.adminserver.domain.admin.controller;

import capstone.zigtong.adminserver.domain.admin.dto.AdminSignInDto;
import capstone.zigtong.adminserver.domain.admin.dto.AdminSignUpDto;
import capstone.zigtong.adminserver.domain.admin.dto.SignInResponse;
import capstone.zigtong.adminserver.domain.admin.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static capstone.zigtong.adminserver.global.security.constant.EndpointConstant.ENDPOINT_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENDPOINT_PREFIX+"/admins")
public class AdminController {
    private final AdminService adminService;
    @Operation(summary = "회원가입", description = "사업자(관리자)의 회원가입입니다")
    @PostMapping("/sign-up")
    public ResponseEntity<Void>adminSignUp(@RequestBody AdminSignUpDto adminSignUpDto){
        adminService.signUp(adminSignUpDto.toDto());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "로그인", description = "사업자(관리자)의 로그인입니다")
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse>adminSignIn(@RequestBody AdminSignInDto adminSignInDto){
        SignInResponse signInResponse = adminService.signIn(adminSignInDto);
        return ResponseEntity.ok()
                .body(new SignInResponse(signInResponse.getAccessToken()));
    }
}
