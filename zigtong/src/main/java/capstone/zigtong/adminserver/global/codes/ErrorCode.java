package capstone.zigtong.adminserver.global.codes;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATED_ACCOUNT_ID(400, "이미 존재하는 계정입니다."),
    ALREADY_EXISTS(409, "Value Already Exists");
    // 에러 코드의 '코드 메시지'을 반환한다.
    private final String message;
    private final int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
