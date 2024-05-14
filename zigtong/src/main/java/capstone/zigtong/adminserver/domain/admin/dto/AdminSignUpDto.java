package capstone.zigtong.adminserver.domain.admin.dto;

import capstone.zigtong.adminserver.domain.admin.Role;
import lombok.Data;

@Data
public class AdminSignUpDto {
    private String accountId;
    private String password;

    private String name;

    private String businessNumber;

    private String category;

    private Role role;

    public AdminDto toDto() {
        return new AdminDto(accountId, password, name, businessNumber, category, role);
    }
}
