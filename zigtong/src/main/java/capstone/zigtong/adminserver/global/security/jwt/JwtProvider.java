package capstone.zigtong.adminserver.global.security.jwt;

import capstone.zigtong.adminserver.global.security.constant.HeaderConstant;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("${jwt.access-token-expiration}")
    public long ACCESS_TOKEN_VALID_MILL_TIME;

    @Value("${jwt.access-token-key}")
    private String ACCESS_SECRET_KEY;

    public String resolveToken(@Nullable HttpServletRequest request, String header) {
        String authHeader = request.getHeader(header);
        if (authHeader == null) {
            return null;
        }
    
        String token = authHeader.replace(HeaderConstant.BEARER_PREFIX, "");

        return token;
    }

    public String generateAccessToken(@Nullable String memberId) {
        final Date now = new Date();

        // access token 생성
        String accessToken =
                Jwts.builder()
                        .setSubject(memberId)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_MILL_TIME))
                        .signWith(getTokenKey())
                        .compact();

        return accessToken;
    }

    public <T> T extractClaim(
            String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        System.out.println("token1 = " + token);

        Key signingKey = getTokenKey();
        System.out.println("signingKey = " + signingKey);
        System.out.println("여기");
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String extractMemberId(String token) {
        return extractClaim(token, (claims) -> claims.getSubject());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getTokenKey() {
        byte[] keyBytes = ACCESS_SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
        //return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }


    public boolean isTokenValid(String token) {
        try {
            return (!isTokenExpired(token));
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token", e);
        } catch (JwtException e) {
            log.error("JWT Token is not valid", e);
        }

        return false;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Authentication getAuthentication(String memberId) {
        return new UsernamePasswordAuthenticationToken(memberId, null, null);
    }
}
