package TodoChallengers.BE.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {
    private UUID participantId;
    private List<ChallengeChecklist> challengeChecklist = new ArrayList<>();;

    public void addChecklist(ChallengeChecklist checklist) {
        if (this.challengeChecklist == null) {
            this.challengeChecklist = new ArrayList<>();
        }
        this.challengeChecklist.add(checklist);
    }
}
