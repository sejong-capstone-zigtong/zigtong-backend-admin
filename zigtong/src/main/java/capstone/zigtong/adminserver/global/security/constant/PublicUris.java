package capstone.zigtong.adminserver.global.security.constant;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum PublicUris {
    SIGN_IN("/admins/sign-in"),
    SIGN_UP("/admins/sign-up"),
    SEND_CODE("/auth/send-code"),
    VERIFY("/auth/verify"),
    BUSINESS_TYPE("/admins/business-type");

    private final String value;

    public static List<String> getAllUrisWithEndpointPrefix() {
        return Arrays.stream(PublicUris.values())
                .map(PublicUris::getUriWithEndpointPrefix)
                .collect(Collectors.toUnmodifiableList());
    }

    private String getUriWithEndpointPrefix() {
        return EndpointConstant.ENDPOINT_PREFIX + value;
    }
}
