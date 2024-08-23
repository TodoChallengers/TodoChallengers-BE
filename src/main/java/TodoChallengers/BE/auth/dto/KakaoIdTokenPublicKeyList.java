package TodoChallengers.BE.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class KakaoIdTokenPublicKeyList {
    private List<KakaoIdTokenJwk> keys;
}