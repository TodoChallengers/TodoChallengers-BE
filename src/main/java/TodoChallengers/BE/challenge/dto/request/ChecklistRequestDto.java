package TodoChallengers.BE.challenge.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Data
@NoArgsConstructor
public class ChecklistRequestDto {
    private String userId;
    //private String token;
    private ChecklistDto checklist;
    @Getter
    @Data
    @NoArgsConstructor
    public static class ChecklistDto{
        private String challengeId;
        private Date checklistDate;
        private MultipartFile checklistPhoto;
    }
}
