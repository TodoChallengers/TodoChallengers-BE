package TodoChallengers.BE.challenge.exception;

import java.util.UUID;

public class ChallengeNotFoundException extends RuntimeException {
    public ChallengeNotFoundException(UUID challengeId){
        super("Challenge with id " + challengeId + " not found");
    }
    public ChallengeNotFoundException(String message) {
        super(message);
    }
}
