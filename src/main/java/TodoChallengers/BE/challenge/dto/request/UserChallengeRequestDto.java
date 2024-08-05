package TodoChallengers.BE.challenge.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
public class UserChallengeRequestDto {
    private String userId; // 사용자 ID
    //private String token; // 추후 추가 예정
    private String challengeId;
}
