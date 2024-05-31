package capstone.zigtong.adminserver.domain.worker.dto;

import capstone.zigtong.adminserver.domain.worker.Gender;
import capstone.zigtong.adminserver.domain.worker.Worker;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class WorkerDto {
    private String id;
    private String name;
    private LocalDate birthdate;
    private String nickname;
    private Gender gender;
    private String uploadUrl;

    public static WorkerDto FromEntityForWorkerApplicationStatusDto(Worker worker) {
        return new WorkerDto(
                worker.getId(),
                worker.getName(),
                worker.getBirthdate(),
                worker.getNickname(),
                worker.getGender(),
                worker.getResume().getUploadedUrl()
        );
    }
}
