package TodoChallengers.BE.challenge.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
public class ReactionRequestDto {
    private String userId;
    //private String token;
    private String checklistId;
}
