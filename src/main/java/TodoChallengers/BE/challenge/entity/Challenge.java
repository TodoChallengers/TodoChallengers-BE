package TodoChallengers.BE.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="challenge")
public class Challenge {
    @Id
    private UUID id;
    private String challengeName;
    private Date start;
    private Date end;
    private String category;
    private UUID challengeLeaderId;
    private String state;
    private List<Participant> participants;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Participant {
    private UUID participantId;
    private List<ChallengeChecklist> challengeChecklist;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ChallengeChecklist {
    private UUID checklistId;
    private Date checklistDate;
    private String checklistPhoto;
    private String state;
    private List<Reaction> reaction;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Reaction {
    private UUID reactionId;
    private UUID reactionGiverId;
}