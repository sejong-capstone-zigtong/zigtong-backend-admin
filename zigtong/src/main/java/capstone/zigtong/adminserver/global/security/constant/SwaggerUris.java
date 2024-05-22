package capstone.zigtong.adminserver.global.security.constant;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum SwaggerUris {
    SWAGGER_UI("/swagger-ui.html"),
    SWAGGER_UI_RESOURCES("/swagger-ui/**"),
    SWAGGER_RESOURCES("/v3/api-docs/**"),
    SWAGGER_RESOURCES_CONFIGURATION("/v3/api-docs/configuration/ui"),
    SWAGGER_RESOURCES_SECURITY("/v3/api-docs/configuration/security"),
    SWAGGER_RESOURCES_SWAGGER_RESOURCES("/v3/api-docs/swagger-resources"),
    SWAGGER_RESOURCES_WEBJARS("/webjars/**");

    private final String value;

    // 전체 URI 반환
    public static List<String> getAllUris() {
        return Arrays.stream(SwaggerUris.values())
                .map(SwaggerUris::getUri)
                .collect(Collectors.toUnmodifiableList());
    }

    private String getUri() {
        return value;
    }
}
