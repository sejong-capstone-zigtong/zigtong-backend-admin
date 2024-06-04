package capstone.zigtong.adminserver.domain.employee.dto;

import capstone.zigtong.adminserver.domain.employee.Employee;
import capstone.zigtong.adminserver.domain.employee.WorkingStatus;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.post.dto.PostDto;
import capstone.zigtong.adminserver.domain.worker.Worker;
import capstone.zigtong.adminserver.domain.worker.dto.WorkerDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private WorkingStatus workingStatus;
    private boolean startAttendanceStatus;
    private boolean endAttendanceStatus;
    private PostDto postDto;
    private WorkerDto workerDto;

    public EmployeeDto(String id, WorkingStatus workingStatus, Post post, Worker worker) {
        this.id = id;
        this.workingStatus = workingStatus;
        this.postDto = PostDto.fromEntity(post);
        this.workerDto = WorkerDto.FromEntityForWorkerApplicationStatusDto(worker);
    }

    public static EmployeeDto fromEntity(Employee employee) {
        Worker worker = employee.getWorker();
        WorkerDto workerDto = WorkerDto.FromEntityForWorkerApplicationStatusDto(worker);
        Post post = employee.getPost();
        PostDto postDto = PostDto.fromEntity(post);
        return new EmployeeDto(
                employee.getId(),
                employee.getWorkingStatus(),
                employee.isStartAttendanceStatus(),
                employee.isEndAttendanceStatus(),
                postDto,
                workerDto
        );
    }
}
