package TodoChallengers.BE.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeResponseDto {
    // 특정 id 값의 챌린지를 가져오는
    private UUID challengeID;
    private String challengeName;
    private UUID challengeLeaderId;
    private Date start;
    private Date end;
    private String category;
    private Set<UUID> participants;
}
