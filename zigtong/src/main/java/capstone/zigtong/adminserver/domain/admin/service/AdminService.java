package capstone.zigtong.adminserver.domain.admin.service;

import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.admin.dto.AdminDto;
import capstone.zigtong.adminserver.domain.admin.repository.AdminRepository;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static capstone.zigtong.adminserver.global.codes.ErrorCode.DUPLICATED_ACCOUNT_ID;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    public void signUp(AdminDto adminDto){
        duplicateVerification(adminDto);
        Admin admin = adminDto.toEntity();
        adminRepository.save(admin);
    }
    private void duplicateVerification(AdminDto adminDto){
        if(adminRepository.existsByAccountId(adminDto.getAccountId())){
            throw new CustomException(DUPLICATED_ACCOUNT_ID);
        }
    }
}
