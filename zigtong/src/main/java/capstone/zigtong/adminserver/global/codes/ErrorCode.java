package capstone.zigtong.adminserver.global.codes;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATED_ACCOUNT_ID(400, "이미 존재하는 계정입니다."),
    ALREADY_EXISTS(409, "Value Already Exists"),
    UNAUTHORIZED(403,"인증되지 않은 사용자입니다."),
    TOKEN_EXPIRED(401, "세션이 만료되었습니다."),
    ACCOUNT_NOT_FOUND(404, "존재하지 않는 계정입니다."),
    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    DUPLICATED_PHONE_NUMBER(400, "이미 존재하는 전화번호입니다.")
    ;
    // 에러 코드의 '코드 메시지'을 반환한다.
    private final String message;
    private final int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
