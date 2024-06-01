package capstone.zigtong.adminserver.domain.resume.repository;

import capstone.zigtong.adminserver.domain.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, String> {

}
