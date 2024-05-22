package capstone.zigtong.adminserver.domain.admin.service;

import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.admin.dto.AdminDto;
import capstone.zigtong.adminserver.domain.admin.repository.AdminRepository;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static capstone.zigtong.adminserver.global.codes.ErrorCode.DUPLICATED_ACCOUNT_ID;
import static capstone.zigtong.adminserver.global.codes.ErrorCode.DUPLICATED_PHONE_NUMBER;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(AdminDto adminDto){
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
}
