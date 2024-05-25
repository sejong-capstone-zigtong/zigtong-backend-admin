package capstone.zigtong.adminserver.global.validation;

import static capstone.zigtong.adminserver.global.validation.Constant.*;

public class Regex {
    public static final String PHONE_NUMBER_REGEX = "^010[0-9]{8}$";
    public static final String MEMBER_ACCOUNT_REGEX =
            "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{" + ACCOUNT_MIN_LENGTH + "," + ACCOUNT_MAX_LENGTH + "}$";
    public static final String PASSWORD_REGEX =
            "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{" + PASSWORD_MIN_LENGTH + "," + PASSWORD_MAX_LENGTH + "}$";
    public static final String NICKNAME_REGEX =
            "^[a-zA-Z0-9가-힣]{" + NICKNAME_MIN_LENGTH + "," + NICKNAME_MAX_LENGTH + "}$";
}
