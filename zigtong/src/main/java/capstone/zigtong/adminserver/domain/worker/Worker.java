package capstone.zigtong.adminserver.domain.worker;

import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Worker {
    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "char(36)")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String memberAccount;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(columnDefinition = "char(11)", nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @OneToMany(mappedBy = "worker")
    private List<WorkerApplicationStatus> workerApplicationStatusList = new ArrayList<>();

    public void addWorkerApplicationStatus(WorkerApplicationStatus workerApplicationStatus) {
        workerApplicationStatusList.add(workerApplicationStatus);
    }

    /*@Builder(access = AccessLevel.PRIVATE)
    private Worker(String name, String memberAccount, String password, LocalDate birthdate, String phoneNumber,
                   String nickname, Gender gender) {
        this.name = name;
        this.memberAccount = memberAccount;
        this.password = password;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.gender = gender;
    }

    public static Worker create(WorkerSignUpRequest request, String encryptedPassword) {
        Worker worker = Worker.builder()
                .name(request.name())
                .memberAccount(request.memberAccount())
                .password(encryptedPassword)
                .birthdate(request.birthdate())
                .phoneNumber(request.phoneNumber())
                .nickname(request.nickname())
                .gender(request.gender())
                .build();

        return worker;
    }*/
}
