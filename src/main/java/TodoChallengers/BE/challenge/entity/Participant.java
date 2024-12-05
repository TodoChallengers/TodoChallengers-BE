package TodoChallengers.BE.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {
    private UUID participantId;
    private Set<ChallengeChecklist> challengeChecklist = new HashSet<>();;

    public void addChecklist(ChallengeChecklist checklist) {
        if (this.challengeChecklist == null) {
            this.challengeChecklist = new HashSet<>();
        }
        this.challengeChecklist.add(checklist);
    }
}
