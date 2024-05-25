package capstone.zigtong.adminserver.domain.employee.service;

import capstone.zigtong.adminserver.domain.employee.dto.EmployeeDto;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.post.repository.PostRepository;
import capstone.zigtong.adminserver.domain.workerApplicationStatus.dto.WorkerApplicationStatusDto;
import capstone.zigtong.adminserver.global.codes.ErrorCode;
import capstone.zigtong.adminserver.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static capstone.zigtong.adminserver.global.codes.ErrorCode.POST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final PostRepository postRepository;
    public List<EmployeeDto> getEmployee(String adminId, String postId) {
        Post post = getPostById(postId);
        return post.getEmployeeList().stream()
                .map(EmployeeDto::fromEntity)
                .toList();
    }
    private Post getPostById(String postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(POST_NOT_FOUND)
        );
        return post;
    }
}
