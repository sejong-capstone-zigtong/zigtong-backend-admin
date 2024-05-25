package capstone.zigtong.adminserver.domain.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static capstone.zigtong.adminserver.global.validation.ErrorMassage.*;
import static capstone.zigtong.adminserver.global.validation.Regex.MEMBER_ACCOUNT_REGEX;
import static capstone.zigtong.adminserver.global.validation.Regex.PASSWORD_REGEX;

@Data
public class AdminSignInDto {
    @NotBlank(message = EMPTY_ACCOUNT)
    @Pattern(regexp = MEMBER_ACCOUNT_REGEX, message = INVALID_ACCOUNT)
    String accountId;
    @NotBlank(message = EMPTY_PASSWORD)
    @Pattern(regexp = PASSWORD_REGEX, message = INVALID_PASSWORD)
    String password;

}
