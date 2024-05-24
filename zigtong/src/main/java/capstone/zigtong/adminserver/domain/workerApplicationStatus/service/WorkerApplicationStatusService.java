package capstone.zigtong.adminserver.domain.workerApplicationStatus.service;


import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.post.repository.PostRepository;
import capstone.zigtong.adminserver.domain.worker.Worker;
import capstone.zigtong.adminserver.domain.worker.repository.WorkerRepository;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusUpdateDto;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.repository.WorkerApplicationStatusRepository;
import capstone.zigtong.adminserver.global.codes.ErrorCode;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerApplicationStatusService {
    private final WorkerRepository workerRepository;
    private final PostRepository postRepository;
    private final WorkerApplicationStatusRepository workerApplicationStatusRepository;
    public WorkerApplicationStatusDto createApplication(String postId, String workerId) {
        Post post = getPostById(postId);
        Worker worker = getWorkerById(workerId);
        WorkerApplicationStatus application = new WorkerApplicationStatus(post, worker);
        return WorkerApplicationStatusDto.fromEntity(application);
    }


    public WorkerApplicationStatusDto updateApplication(String postId, String workerApplicationId,
                                                        WorkerApplicationStatusUpdateDto requestDto) {
        WorkerApplicationStatus workerApplicationStatus = getWorkerApplicationStatus(workerApplicationId);

        //Admin인지 확인하는 절차 필요. getPrincipal의 accountId와 post의 admin이 같은 지 비교해야함
        workerApplicationStatus.updateByDto(requestDto);
        return WorkerApplicationStatusDto.fromEntity(workerApplicationStatus);
    }
    private Post getPostById(String postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.POST_NOT_FOUND)
        );
        return post;
    }
    private Worker getWorkerById(String workerId){
        Worker worker = workerRepository.findById(workerId).orElseThrow(
                () -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND)
        );
        return worker;
    }
    private WorkerApplicationStatus getWorkerApplicationStatus(String workerApplicationStatusId){
        WorkerApplicationStatus workerApplicationStatus = workerApplicationStatusRepository.findById(workerApplicationStatusId).orElseThrow(
                () -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND)
        );
        return workerApplicationStatus;
    }
}
