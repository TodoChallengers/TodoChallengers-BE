package TodoChallengers.BE.auth.service;


import TodoChallengers.BE.auth.component.KakaoUserInfo;
import TodoChallengers.BE.auth.dto.KakaoUserInfoResponse;
import TodoChallengers.BE.user.domain.User;
import TodoChallengers.BE.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KakaoAuthService {

    private final KakaoUserInfo kakaoUserInfo;
    private final UserRepository userRepository;

    @Transactional
    public Long userLogin(String token) {
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(token);
        Optional<User> user = userRepository.findByKakaoId(userInfo.getId());
        if(user.isPresent()) {
            return user.get().getId();
        }
        else {
            User newUser = User.builder().kakaoId(userInfo.getId()).nickname(userInfo.getKakao_account().getProfile().getNickname()).profileImage(userInfo.getKakao_account().getProfile().getProfile_image_url()).build();
            return userRepository.save(newUser).getId();
        }
    }
}
