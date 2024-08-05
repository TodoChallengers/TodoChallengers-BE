package TodoChallengers.BE.challenge.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserChallengeRequestDto {
    private String id; // 사용자 ID
    //private String token; // 추후 추가 예정
    private String challengeId;
}
