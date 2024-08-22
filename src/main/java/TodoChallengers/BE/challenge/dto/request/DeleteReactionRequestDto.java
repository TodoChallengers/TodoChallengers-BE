package TodoChallengers.BE.challenge.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Data
@NoArgsConstructor
public class DeleteReactionRequestDto {
    private UUID userId;
    //private String token;
    private UUID challengeId;
    private UUID checklistId;
    private UUID reactionId;
}
