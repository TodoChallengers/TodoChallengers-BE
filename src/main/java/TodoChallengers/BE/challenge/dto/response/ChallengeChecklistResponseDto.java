package TodoChallengers.BE.challenge.dto.response;

import TodoChallengers.BE.challenge.entity.ChallengeChecklist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeChecklistResponseDto {
    private UUID challengeId;
    private String challengeName;
    private List<ParticipantChecklist> participants;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParticipantChecklist {
        private UUID participantId;
        private List<ChallengeChecklist> checklists;
    }
}
