package capstone.zigtong.adminserver.domain.workerApplicationStatus.service;

import capstone.zigtong.adminserver.domain.admin.Admin;
import capstone.zigtong.adminserver.domain.admin.repository.AdminRepository;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.post.repository.PostRepository;
import capstone.zigtong.adminserver.domain.worker.Worker;
import capstone.zigtong.adminserver.domain.worker.repository.WorkerRepository;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.WorkerApplicationStatus;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusDto;
import capstone.zigtong.adminserver.global.codes.ErrorCode;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerApplicationStatusService {
    private final WorkerRepository workerRepository;
    private final PostRepository postRepository;
    public WorkerApplicationStatusDto createApplication(String postId, String workerId) {
        Post post = getPostById(postId);
        Worker worker = getWorkerById(workerId);
        WorkerApplicationStatus application = new WorkerApplicationStatus(post, worker);
        return WorkerApplicationStatusDto.fromEntity(application);
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
}
