package TodoChallengers.BE.challenge.repository;

import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ChallengeRepository extends MongoRepository<Challenge, UUID> {
    List<Challenge> findByState(String state);
    List<Challenge> findByParticipantsParticipantId(UUID userId);
}
