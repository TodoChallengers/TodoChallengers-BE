package TodoChallengers.BE.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    @Column(unique = true, nullable = false)
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
