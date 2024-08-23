package TodoChallengers.BE.auth.service;

import TodoChallengers.BE.auth.dto.LoginResponse;
import TodoChallengers.BE.auth.dto.ReissueResponse;

public interface AuthService {
    LoginResponse loginWithKakao(String code);

    ReissueResponse reissue(String authorizationHeader);
    void deleteExpiredRefreshTokens();

    Boolean validateToken(String authorizationHeader);
}