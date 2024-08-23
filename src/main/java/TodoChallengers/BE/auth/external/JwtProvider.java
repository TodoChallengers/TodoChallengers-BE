package TodoChallengers.BE.auth.external;

import TodoChallengers.BE.auth.config.JwtProperties;
import TodoChallengers.BE.common.exception.AuthException;
import TodoChallengers.BE.common.util.ResponseCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider implements InitializingBean {
    private final JwtProperties jwtProperties;
    private Key secretkey;
    private final String ISS = "http://www.todochallengers.com";

    @Override
    public void afterPropertiesSet() {
        byte[] secretKeyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        this.secretkey = Keys.hmacShaKeyFor(secretKeyBytes);
    }

    public String createAccessToken(String kakaoId, String refreshId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.getAccessTokenValidityInSeconds() * 1000);

        return Jwts.builder()
                .issuer(ISS) // 토큰을 발급한 인증 기관 정보
                .subject(kakaoId) // 토큰에 해당하는 사용자의 kakao_id
                .issuedAt(now) // 토큰 발급 또는 갱신 시각
                .expiration(expiration) // 토큰 만료 시간
                .claim("refreshId", refreshId)
                .signWith(secretkey)
                .compact();
    }

    public String createRefreshToken(String kakaoId, String userName) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + +jwtProperties.getRefreshTokenValidityInSeconds() * 1000);

        return Jwts.builder()
                .issuer(ISS) // 토큰을 발급한 인증 기관 정보
                .subject(kakaoId) // 토큰에 해당하는 사용자의 kakao_id
                .issuedAt(now) // 토큰 발급 또는 갱신 시각
                .expiration(expiration) // 토큰 만료 시간
                .claim("userName", userName)
                .signWith(secretkey)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) secretkey)
                    .build()
                    .parseSignedClaims(token);
        } catch (SecurityException | MalformedJwtException e) {
            throw new AuthException(ResponseCode.UNAUTHORIZED); // 잘못된 서명
        } catch (ExpiredJwtException e) {
            throw new AuthException(ResponseCode.UNAUTHORIZED); // 만료
        } catch (UnsupportedJwtException e) {
            throw new AuthException(ResponseCode.UNAUTHORIZED); // 지원되지 않는 토큰
        } catch (IllegalArgumentException e) {
            throw new AuthException(ResponseCode.BAD_REQUEST); // 잘못된 토큰
        }
    }

    public String getKakaoIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) secretkey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject(); // 토큰에 해당하는 사용자의 kakao_id
    }

    public String getUserNameFromRefreshToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) secretkey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("userName", String.class); // 토큰에 해당하는 사용자의 username
    }

    public Date getExpirationFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) secretkey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public String getKakaoIdFromExpiredToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) secretkey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject(); // 토큰에 해당하는 사용자의 kakao_id
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public String getRefreshIdFromExpiredToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) secretkey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("refreshId", String.class);
        } catch (ExpiredJwtException e) {
            return e.getClaims().get("refreshId", String.class);
        }
    }

    public String getTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new AuthException(ResponseCode.BAD_REQUEST);
    }

}
