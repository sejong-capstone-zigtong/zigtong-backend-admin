package capstone.zigtong.adminserver.global.security.filter;

import capstone.zigtong.adminserver.global.codes.ErrorCode;
import capstone.zigtong.adminserver.global.exception.CustomException;
import capstone.zigtong.adminserver.global.security.constant.HeaderConstant;
import capstone.zigtong.adminserver.global.security.constant.PublicUris;
import capstone.zigtong.adminserver.global.security.constant.SwaggerUris;
import capstone.zigtong.adminserver.global.security.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = jwtProvider.resolveToken(request, HeaderConstant.AUTHORIZATION);

        if (accessToken == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        boolean isAccessTokenValid = jwtProvider.isTokenValid(accessToken);
        if (!isAccessTokenValid) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        }

        String memberId = jwtProvider.extractMemberId(accessToken);
        SecurityContextHolder.getContext()
                .setAuthentication(jwtProvider.getAuthentication(memberId));

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return PublicUris.getAllUrisWithEndpointPrefix()
                .stream()
                .anyMatch(uri -> new AntPathRequestMatcher(uri).matches(request)) ||
                SwaggerUris.getAllUris()
                        .stream()
                        .anyMatch(uri -> new AntPathRequestMatcher(uri).matches(request));
    }
}
