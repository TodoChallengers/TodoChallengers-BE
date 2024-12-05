package TodoChallengers.BE.challenge.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Data
@NoArgsConstructor
public class PublicChallengeRequestDto {
    //private String id;  // jwt 구현 시 추가 예정
    //private String token;
    private String challengeName;
    private LocalDate start;
    private LocalDate end;
    private String category;
    private String state; // 기본 값 공개로 설정
    private UUID challengeLeaderId;
}
