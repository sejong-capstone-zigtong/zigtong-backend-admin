package capstone.zigtong.adminserver.domain.admin.repository;

import capstone.zigtong.adminserver.domain.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
    boolean existsByAccountId(String accountId);
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Admin> findByAccountId(String accountId);
}
