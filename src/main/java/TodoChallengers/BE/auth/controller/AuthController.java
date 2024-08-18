package TodoChallengers.BE.auth.controller;

import TodoChallengers.BE.auth.dto.LoginResponse;
import TodoChallengers.BE.auth.dto.ReissueResponse;
import TodoChallengers.BE.auth.service.AuthService;
import TodoChallengers.BE.common.util.ApiResponse;
import TodoChallengers.BE.common.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login/oauth/kakao")
    public ApiResponse<LoginResponse> loginWithKakao(@RequestParam("code") String code) {
        return ApiResponse.success(authService.loginWithKakao(code), ResponseCode.USER_LOGIN_SUCCESS.getMessage());
    }

    @GetMapping("/reissue")
    public ApiResponse<ReissueResponse> reissue(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        return ApiResponse.success(authService.reissue(authorizationHeader), ResponseCode.USER_TOKEN_REISSUE_SUCCESS.getMessage());
    }

    @GetMapping("/validate")
    public ApiResponse<Boolean> validateToken(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        return ApiResponse.success(authService.validateToken(authorizationHeader), ResponseCode.USER_TOKEN_VALIDATE_SUCCESS.getMessage());
    }
}
