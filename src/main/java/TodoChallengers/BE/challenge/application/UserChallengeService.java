package TodoChallengers.BE.challenge.application;

import TodoChallengers.BE.challenge.entity.Challenge;
import TodoChallengers.BE.challenge.entity.Participant;
import TodoChallengers.BE.challenge.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge participateInChallenge(UUID userId, UUID challengeId) {
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);
        if (optionalChallenge.isPresent()) {
            Challenge challenge = optionalChallenge.get();
            Participant participant = Participant.builder().participantId(userId).build();
            challenge.getParticipants().add(participant);
            return challengeRepository.save(challenge);
        } else {
            throw new IllegalArgumentException("해당 챌린지가 존재하지 않습니다~");
        }
    }
}
