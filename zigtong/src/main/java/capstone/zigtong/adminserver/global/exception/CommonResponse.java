package capstone.zigtong.adminserver.global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponse {
    String id;
    String message;
}
