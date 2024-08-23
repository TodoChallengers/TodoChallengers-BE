package TodoChallengers.BE.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoIdTokenPayload {
    private String aud;
    private String sub;
    private String iss;
    private Long exp;
    private String nickname;

    @Builder
    public KakaoIdTokenPayload(String aud, String sub, String iss, Long exp, String nickname) {
        this.aud = aud;
        this.sub = sub;
        this.iss = iss;
        this.exp = exp;
        this.nickname = nickname;
    }
}
