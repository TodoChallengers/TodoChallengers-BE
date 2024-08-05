package TodoChallengers.BE.auth.controller;

import TodoChallengers.BE.auth.component.JwtTokenProvider;
import TodoChallengers.BE.auth.dto.ResponseJwtDto;
import TodoChallengers.BE.auth.service.KakaoAuthService;
import TodoChallengers.BE.common.util.ApiResponse;
import TodoChallengers.BE.common.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final KakaoAuthService kakaoAuthService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입 여부 확인
     * 회원이면 token 발급
     * @param accessToken
     * @return
     */
    @PostMapping("/login")
    public ApiResponse<ResponseJwtDto> authCheck(@RequestHeader String accessToken) {
        Long userId = kakaoAuthService.userLogin(accessToken); // 유저 고유번호 추출
        String userJwt = jwtTokenProvider.createToken(userId.toString());
        return ApiResponse.success(ResponseJwtDto.of(userId, userJwt), ResponseCode.USER_LOGINED.getMessage());
    }
}
