package capstone.zigtong.adminserver.domain.employee.repository;

import capstone.zigtong.adminserver.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
