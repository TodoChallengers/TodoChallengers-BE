package TodoChallengers.BE.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String id;

    private String nickname;

    @Indexed(unique = true)
    private String kakaoId;

    private LocalDateTime createDate;

    private LocalDateTime requestDate;

    @Builder
    public User(String nickname, String kakaoId, LocalDateTime createDate, LocalDateTime requestDate) {
        this.nickname = nickname;
        this.kakaoId = kakaoId;
        this.createDate = createDate;
        this.requestDate = requestDate;
    }

    public void updateRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
