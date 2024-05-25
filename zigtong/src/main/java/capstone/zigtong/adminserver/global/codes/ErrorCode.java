package capstone.zigtong.adminserver.global.codes;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 서버 에러
    METHOD_ARGUMENT_TYPE_MISMATCH(400, "Enum Type이 일치하지 않아 Binding에 실패하였습니다."),
    METHOD_NOT_ALLOWED(405, "지원하지 않는 HTTP method 입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류, 관리자에게 문의하세요"),
    DUPLICATED_ACCOUNT_ID(400, "이미 존재하는 계정입니다."),
    ALREADY_EXISTS(409, "Value Already Exists"),
    UNAUTHORIZED(403,"인증되지 않은 사용자입니다."),
    TOKEN_EXPIRED(401, "세션이 만료되었습니다."),
    ACCOUNT_NOT_FOUND(404, "존재하지 않는 계정입니다."),
    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    DUPLICATED_PHONE_NUMBER(400, "이미 존재하는 전화번호입니다."),
    INVALID_BUSINESS_REGISTRATION(400, "유효하지 않은 사업자등록번호입니다."),
    MISMATCHED_PASSWORD(400, "비밀번호가 일치하지 않습니다.")
    ;
    // 에러 코드의 '코드 메시지'을 반환한다.
    private final String message;
    private final int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
