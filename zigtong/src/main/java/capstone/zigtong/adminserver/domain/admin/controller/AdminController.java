package capstone.zigtong.adminserver.domain.admin.controller;

import capstone.zigtong.adminserver.domain.admin.dto.AdminSignUpDto;
import capstone.zigtong.adminserver.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/api/admins/sign-up")
    public ResponseEntity<Void>adminSignUp(@RequestBody AdminSignUpDto adminSignUpDto){
        adminService.signUp(adminSignUpDto.toDto());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
