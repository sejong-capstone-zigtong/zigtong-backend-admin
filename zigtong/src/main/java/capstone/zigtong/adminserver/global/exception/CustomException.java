package capstone.zigtong.adminserver.global.exception;

import capstone.zigtong.adminserver.global.codes.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        //super(message);
        this.errorCode = errorCode;
    }
}
