package TodoChallengers.BE.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionResponseDto {
    private UUID checklistId;
    private Set<ReactionDetail> reactionDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReactionDetail {
        private UUID reactionId;
        private UUID reactionGiverId;
    }
}
