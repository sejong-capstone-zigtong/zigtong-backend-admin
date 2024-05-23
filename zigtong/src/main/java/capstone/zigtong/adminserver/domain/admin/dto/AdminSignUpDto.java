package capstone.zigtong.adminserver.domain.admin.dto;

import capstone.zigtong.adminserver.domain.admin.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static capstone.zigtong.adminserver.global.validation.ErrorMassage.*;
import static capstone.zigtong.adminserver.global.validation.Regex.*;

@Data
public class AdminSignUpDto {
    @NotBlank
    @Pattern(regexp = MEMBER_ACCOUNT_REGEX, message = INVALID_MEMBER_ACCOUNT)
    private String accountId;
    @NotBlank
    @Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD)
    private String password;
    @NotBlank
    @Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD)
    private String passwordCheck;
    @NotBlank
    @Pattern(regexp = NICKNAME_REGEX)
    private String name;
    @NotBlank
    private String companyName;
    @NotBlank
    private String businessName;
    @NotBlank()
    @Pattern(regexp = PHONE_NUMBER_REGEX, message = INVALID_PHONE_NUMBER )
    private String phoneNumber;
    @NotBlank
    private String businessNumber;
    @NotBlank
    private String category;
    @NotBlank
    private Role role;

    public AdminDto toDto() {
        return new AdminDto(accountId, password, name,companyName, businessName, phoneNumber, businessNumber, category, role);
    }
}
