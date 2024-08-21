package TodoChallengers.BE.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeChecklist {
    private UUID checklistId;
    private Date checklistDate;
    private String checklistPhoto;
    private String state;
    private List<Reaction> reaction;

    public void setReaction(List<Reaction> reaction) {
        this.reaction = reaction;
    }

    public void addReaction(Reaction reaction) {
        if (this.reaction == null) {
            this.reaction = new ArrayList<>();
        }
        this.reaction.add(reaction);
    }
}
