package capstone.zigtong.adminserver.domain.employee.dto;

import capstone.zigtong.adminserver.domain.employee.Employee;
import capstone.zigtong.adminserver.domain.employee.Workingstatus;
import capstone.zigtong.adminserver.domain.post.Post;
import capstone.zigtong.adminserver.domain.worker.Worker;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private Workingstatus workingstatus;
    private boolean startAttendanceStatus;
    private boolean endAttendanceStatus;
    private Post post;
    private Worker worker;

    public EmployeeDto(String id, Workingstatus workingstatus, Post post, Worker worker) {
        this.id = id;
        this.workingstatus = workingstatus;
        this.post = post;
        this.worker = worker;
    }

    public static EmployeeDto fromEntity(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getWorkingstatus(),
                employee.getPost(),
                employee.getWorker()
        );
    }
}
