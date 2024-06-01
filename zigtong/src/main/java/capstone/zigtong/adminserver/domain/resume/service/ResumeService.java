package capstone.zigtong.adminserver.domain.resume.service;

import capstone.zigtong.adminserver.domain.resume.Resume;
import capstone.zigtong.adminserver.domain.resume.dto.ResumeDto;
import capstone.zigtong.adminserver.domain.resume.repository.ResumeRepository;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static capstone.zigtong.adminserver.global.codes.ErrorCode.ACCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    public ResumeDto getResume(String workerId) {
        Resume resume = resumeRepository.findById(workerId).orElseThrow(
                () -> new CustomException(ACCOUNT_NOT_FOUND)
        );
        return ResumeDto.fromEntity(resume);
    }
}
