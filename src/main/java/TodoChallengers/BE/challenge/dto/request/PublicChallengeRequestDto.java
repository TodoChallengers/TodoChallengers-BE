package TodoChallengers.BE.challenge.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter

@Data
@NoArgsConstructor
public class PublicChallengeRequestDto {
    //private String id;  // jwt 구현 시 추가 예정
    //private String token;
    private String challengeName;
    private Date start;
    private Date end;
    private String category;
    private String state; // 기본 값 공개로 설정
    private String challengeLeaderId;
}
