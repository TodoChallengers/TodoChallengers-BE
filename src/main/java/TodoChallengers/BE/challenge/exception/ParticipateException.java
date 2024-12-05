package TodoChallengers.BE.challenge.exception;

import java.util.UUID;

public class ParticipateException extends RuntimeException {
    private final int responseCode;

    // 사용자를 추가하려고 했는데 이미 챌린지에 참여한 경우
    public ParticipateException(UUID userId, UUID challengeId) {
        super("User " + userId + " has already participated in challenge " + challengeId);
        this.responseCode = 400; // Bad Request
    }

    // 사용자를 지우려고 했는데 챌린지에 참여하지 않은 경우
    public ParticipateException(UUID userId, UUID challengeId, boolean isNotParticipated) {
        super("User " + userId + " is not participating in challenge " + challengeId);
        this.responseCode = 400; // Bad Request
    }

    public int getResponseCode() {
        return responseCode;
    }
}
