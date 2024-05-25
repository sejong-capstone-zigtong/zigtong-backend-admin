package capstone.zigtong.adminserver.domain.workerApplicationStatus.service;


import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.employee.Employee;
import capstone.zigtong.adminserver.domain.employee.repository.EmployeeRepository;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.post.repository.PostRepository;
import capstone.zigtong.adminserver.domain.worker.Worker;
import capstone.zigtong.adminserver.domain.worker.repository.WorkerRepository;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.ApplicationStatus;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusUpdateDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.repository.WorkerApplicationStatusRepository;
import capstone.zigtong.adminserver.global.codes.ErrorCode;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import static capstone.zigtong.adminserver.global.codes.ErrorCode.ACCOUNT_NOT_FOUND;
import static capstone.zigtong.adminserver.global.codes.ErrorCode.POST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WorkerApplicationStatusService {
    private final WorkerRepository workerRepository;
    private final PostRepository postRepository;
    private final WorkerApplicationStatusRepository workerApplicationStatusRepository;
    private final EmployeeRepository employeeRepository;
    @Transactional
    public WorkerApplicationStatusDto createApplication(String postId, String workerId) {
        Post post = getPostById(postId);
        Worker worker = getWorkerById(workerId);
        WorkerApplicationStatus application = new WorkerApplicationStatus(post, worker);
        workerApplicationStatusRepository.save(application);
        return WorkerApplicationStatusDto.fromEntity(application);
    }

    @Transactional
    public WorkerApplicationStatusDto updateApplication(String postId, String workerApplicationId,
                                                        WorkerApplicationStatusUpdateDto requestDto) {
        WorkerApplicationStatus workerApplicationStatus = getWorkerApplicationStatus(workerApplicationId);
        Post post = getPostById(postId);
        //Admin인지 확인하는 절차 필요. getPrincipal의 accountId와 post의 admin이 같은 지 비교해야함
        workerApplicationStatus.updateByDto(requestDto);
        if(requestDto.getApplicationStatus().equals(ApplicationStatus.ACCEPT)){
            Employee employee = new Employee(post, workerApplicationStatus.getWorker());
            employeeRepository.save(employee);
        }
        /*else{
            workerApplicationStatusRepository.delete(workerApplicationStatus);
        }*/
        return WorkerApplicationStatusDto.fromEntity(workerApplicationStatus);
    }

    public List<WorkerApplicationStatusDto> getApplications(String postId) {
        Post post = getPostById(postId);
        return post.getWorkerApplicationStatusList().stream()
                .map(WorkerApplicationStatusDto::fromEntity)
                .toList();
    }
    private Post getPostById(String postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(POST_NOT_FOUND)
        );
        return post;
    }
    private Worker getWorkerById(String workerId){
        Worker worker = workerRepository.findById(workerId).orElseThrow(
                () -> new CustomException(ACCOUNT_NOT_FOUND)
        );
        return worker;
    }
    private WorkerApplicationStatus getWorkerApplicationStatus(String workerApplicationStatusId){
        WorkerApplicationStatus workerApplicationStatus = workerApplicationStatusRepository.findById(workerApplicationStatusId).orElseThrow(
                () -> new CustomException(ACCOUNT_NOT_FOUND)
        );
        return workerApplicationStatus;
    }


}
