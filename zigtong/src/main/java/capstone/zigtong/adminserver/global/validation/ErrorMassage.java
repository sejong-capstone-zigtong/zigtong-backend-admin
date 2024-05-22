package capstone.zigtong.adminserver.global.validation;

import static capstone.zigtong.adminserver.global.validation.Constant.MEMBER_ACCOUNT_MAX_LENGTH;
import static capstone.zigtong.adminserver.global.validation.Constant.MEMBER_ACCOUNT_MIN_LENGTH;

public class ErrorMassage {
    public final static String EMPTY_MEMBER_ACCOUNT = "아이디를 입력해주세요.";
    public final static String EMPTY_PASSWORD = "비밀번호를 입력해주세요.";
    public final static String INVALID_PHONE_NUMBER = "유효하지 않은 형식의 휴대폰 번호입니다.";
    //회원가입
    public final static String INVALID_MEMBER_ACCOUNT =
            "아이디는 영문 소문자와 숫자로 이루어진 " + MEMBER_ACCOUNT_MIN_LENGTH + "자 이상, " + MEMBER_ACCOUNT_MAX_LENGTH
                    + "자 이하로 이루어져야 합니다.";
    public final static String INVALID_PASSWORD = "비밀번호는 영문 대소문자와 숫자로 이루어진 " + 10+ "자 이상, "
            + 18 + "자 이하로 이루어져야 합니다.";
}
