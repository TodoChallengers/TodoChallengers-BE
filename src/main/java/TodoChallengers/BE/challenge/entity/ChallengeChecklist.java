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
public class ChallengeChecklist {
    private UUID checklistId;
    private Date checklistDate;
    private String checklistPhoto;
    private String state;
    private Set<Reaction> reaction = new HashSet<>();

    public void setReaction(Set<Reaction> reaction) {
        this.reaction = reaction;
    }

    public void addReaction(Reaction reaction) {
        if (this.reaction == null) {
            this.reaction = new HashSet<>();
        }
        this.reaction.add(reaction);
    }
}
