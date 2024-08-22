package TodoChallengers.BE.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicChallengeResponseDto {
    private UUID challengeId;
    private String challengeName;
}
