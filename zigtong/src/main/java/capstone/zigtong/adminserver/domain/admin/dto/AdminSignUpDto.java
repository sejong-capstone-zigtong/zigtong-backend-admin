package capstone.zigtong.adminserver.domain.admin.dto;

import capstone.zigtong.adminserver.domain.admin.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static capstone.zigtong.adminserver.global.validation.ErrorMassage.*;
import static capstone.zigtong.adminserver.global.validation.Regex.*;

@Data
public class AdminSignUpDto {
    @NotNull
    @Pattern(regexp = MEMBER_ACCOUNT_REGEX, message = INVALID_ACCOUNT)
    private String accountId;
    @NotNull
    @Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD)
    private String password;
    @NotNull
    @Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD)
    private String passwordCheck;
    @NotNull
    @Pattern(regexp = NICKNAME_REGEX)
    private String name;
    @NotNull
    private String companyName;
    @NotNull
    private String businessName;
    @NotNull
    private String address;
    @NotNull
    @Pattern(regexp = PHONE_NUMBER_REGEX, message = INVALID_PHONE_NUMBER )
    private String phoneNumber;
    @NotNull
    private String businessNumber;
    @NotNull
    private String category;
    @NotNull
    private Role role;

    public AdminDto toDto() {
        return new AdminDto(accountId, password, name,companyName, businessName, address, phoneNumber, businessNumber, category, role);
    }
}
