package TodoChallengers.BE.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="challenge")
public class Challenge {
    @Id
    private UUID id;
    private String challengeName;
    private LocalDate start;
    private LocalDate end;
    private String category;
    private UUID challengeLeaderId;
    private String state;
    private Set<Participant> participants;
}

