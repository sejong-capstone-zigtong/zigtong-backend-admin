package capstone.zigtong.adminserver.domain.workspacePost;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkspacePostDto {
    private String url;

    public static WorkspacePostDto fromEntity(WorkspacePost workspacePost) {
        return new WorkspacePostDto((workspacePost.getUrl()));
    }
}
