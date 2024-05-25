package capstone.zigtong.adminserver.domain.admin.service;

import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.admin.dto.AdminDto;
import capstone.zigtong.adminserver.domain.admin.repository.AdminRepository;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static capstone.zigtong.adminserver.global.codes.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    @Transactional
    public void signUp(AdminDto adminDto){
        verifyBusinessRegistration(adminDto.getBusinessNumber());
        duplicateVerification(adminDto);
        String encryptedPassword = passwordEncoder.encode(adminDto.getPassword());
        Admin admin = adminDto.toEntity(encryptedPassword);
        adminRepository.save(admin);
    }
    private void duplicateVerification(AdminDto adminDto){
        if(adminRepository.existsByAccountId(adminDto.getAccountId())){
            throw new CustomException(DUPLICATED_ACCOUNT_ID);
        }
        if(adminRepository.existsByPhoneNumber(adminDto.getPhoneNumber())){
            throw new CustomException(DUPLICATED_PHONE_NUMBER);
        }
    }

    private void verifyBusinessRegistration(String businessNumber) {
        String url = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=Lqfk8fMXREHPKOEQwgfseb25DBkXLAgrqgxeQ2K9/NEdTgKF7tAqNRYXyqaDjxSdPmIP3BQh0Jb2JGyPDdeioQ==";

        Map<String, Object> request = new HashMap<>();
        request.put("b_no", Collections.singletonList(businessNumber));
        System.out.println("request = " + request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        System.out.println("entity = " + entity);
        try {
            System.out.println("1");
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            System.out.println("response = " + response);
            Map responseBody = response.getBody();
            System.out.println("3");
            if (responseBody != null) {
                System.out.println("4");
                List<Map<String, Object>> data = (List<Map<String, Object>>) responseBody.get("data");
                System.out.println("5");
                if (data == null || data.isEmpty() || !"01".equals(data.get(0).get("b_stt_cd"))) {
                    System.out.println("6");
                    throw new CustomException(INVALID_BUSINESS_REGISTRATION);
                }
            } else {
                throw new CustomException(INVALID_BUSINESS_REGISTRATION);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new CustomException(INVALID_BUSINESS_REGISTRATION);
        }
    }
}
