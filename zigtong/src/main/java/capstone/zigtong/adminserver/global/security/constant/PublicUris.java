package capstone.zigtong.adminserver.global.security.constant;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum PublicUris {
    SIGN_IN(EndpointConstant.ENDPOINT_PREFIX +"/admins/sign-in"),
    SIGN_UP(EndpointConstant.ENDPOINT_PREFIX +"/admins/sign-up"),
    SEND_CODE(EndpointConstant.ENDPOINT_PREFIX +"/auth/send-code"),
    VERIFY(EndpointConstant.ENDPOINT_PREFIX +"/auth/verify"),
    BUSINESS_TYPE(EndpointConstant.ENDPOINT_PREFIX +"/admins/business-type"),
    HEALTHY_CHECK("/health-check");

    private final String value;

    public static List<String> getAllUrisWithEndpointPrefix() {
        return Arrays.stream(PublicUris.values())
                .map(PublicUris::getUriWithEndpointPrefix)
                .collect(Collectors.toUnmodifiableList());
    }

    private String getUriWithEndpointPrefix() {
        return value;
    }
}
