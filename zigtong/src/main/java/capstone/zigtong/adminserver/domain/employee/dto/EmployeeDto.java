package capstone.zigtong.adminserver.domain.employee.dto;

import capstone.zigtong.adminserver.domain.employee.Employee;
import capstone.zigtong.adminserver.domain.employee.WorkingStatus;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.worker.Worker;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private WorkingStatus workingStatus;
    private boolean startAttendanceStatus;
    private boolean endAttendanceStatus;
    private Post post;
    private Worker worker;

    public EmployeeDto(String id, WorkingStatus workingStatus, Post post, Worker worker) {
        this.id = id;
        this.workingStatus = workingStatus;
        this.post = post;
        this.worker = worker;
    }

    public static EmployeeDto fromEntity(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getWorkingStatus(),
                employee.getPost(),
                employee.getWorker()
        );
    }
}
