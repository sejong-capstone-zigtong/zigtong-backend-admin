package capstone.zigtong.adminserver.domain.businessType.repository;

import capstone.zigtong.adminserver.domain.businessType.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer> {
}
