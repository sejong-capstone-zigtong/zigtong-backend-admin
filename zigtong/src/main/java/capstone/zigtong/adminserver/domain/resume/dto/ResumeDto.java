package capstone.zigtong.adminserver.domain.resume.dto;

import capstone.zigtong.adminserver.domain.certificate.Certificate;
import capstone.zigtong.adminserver.domain.certificate.dto.CertificateDto;
import capstone.zigtong.adminserver.domain.resume.Resume;
import capstone.zigtong.adminserver.domain.skill.dto.SkillDto;
import capstone.zigtong.adminserver.domain.worker.Gender;
import capstone.zigtong.adminserver.domain.worker.Worker;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResumeDto {
    private final String id;
    String name;
    String phoneNumber;
    private final String uploadedUrl;
    private final String statement;
    String profileImageUrl;
    String content;
    LocalDate birthdate;
    Gender gender;
    private final List<SkillDto> skillDtoList;
    private final List<CareerDto> careerDtoList;
    private final List<CertificateDto> certificateDtoList;
    List<SkillDto> skills;
    List<CareerDto> careers;
    List<CertificateDto> certificates;

    public ResumeDto(String id, String phoneNumber, String uploadedUrl, String statement, LocalDate birthdate, Gender gender, List<SkillDto> skillDtoList, List<CareerDto> careerDtoList, List<CertificateDto> certificateDtoList) {

        this.id = id;
        this.phoneNumber = phoneNumber;
        this.uploadedUrl = uploadedUrl;
        this.statement = statement;
        this.birthdate = birthdate;
        this.gender = gender;
        this.skillDtoList = skillDtoList;
        this.careerDtoList = careerDtoList;
        this.certificateDtoList = certificateDtoList;
    }

    public static ResumeDto fromEntity(Resume resume){
        Worker worker = resume.getWorker();

        List<CertificateDto> certificateDtoList = resume.getResumeCertificateRelations().stream()
                .map(relation -> new CertificateDto(relation.getCertificate()))
                .collect(Collectors.toUnmodifiableList());

        List<SkillDto> skillDtoList = resume.getResumeSkillRelations().stream()
                .map(relation -> new SkillDto(relation.getSkill()))
                .collect(Collectors.toUnmodifiableList());

        List<CareerDto> careerDtoList = resume.getCareers().stream()
                .map(CareerDto::new)
                .collect(Collectors.toUnmodifiableList());
        return new ResumeDto(
                resume.getId(),
                worker.getPhoneNumber(),
                resume.getUploadedUrl(),
                resume.getStatement(),
                worker.getBirthdate(),
                worker.getGender(),
                skillDtoList,
                careerDtoList,
                certificateDtoList
        );
    }
}
