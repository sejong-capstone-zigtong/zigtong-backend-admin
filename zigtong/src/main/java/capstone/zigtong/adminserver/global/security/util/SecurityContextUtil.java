package capstone.zigtong.adminserver.global.security.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {
    public static String extractAdminId() {
        return (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
