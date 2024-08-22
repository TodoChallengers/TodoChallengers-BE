package TodoChallengers.BE.challenge.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Data
@NoArgsConstructor
public class ChecklistRequestDto {
    private UUID userId;
    //private String token;
    private ChecklistDto checklist;
    @Getter
    @Data
    @NoArgsConstructor
    public static class ChecklistDto{
        private UUID challengeId;
        private LocalDate checklistDate;
        private MultipartFile checklistPhoto;
    }
}
