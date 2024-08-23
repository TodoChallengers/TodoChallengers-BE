package TodoChallengers.BE.challenge.application;

import TodoChallengers.BE.challenge.entity.Challenge;
import TodoChallengers.BE.challenge.entity.Participant;
import TodoChallengers.BE.challenge.exception.ChallengeNotFoundException;
import TodoChallengers.BE.challenge.exception.ParticipateException;
import TodoChallengers.BE.challenge.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge participateInChallenge(UUID userId, UUID challengeId) {
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);
//        if (optionalChallenge.isPresent()) {
//            Challenge challenge = optionalChallenge.get();
//            Participant participant = Participant.builder().participantId(userId).build();
//            challenge.getParticipants().add(participant);
//            return challengeRepository.save(challenge);
//        } else {
//            throw new IllegalArgumentException("해당 챌린지가 존재하지 않습니다~");
//        }
        if(optionalChallenge.isEmpty()) {
            throw new ChallengeNotFoundException(challengeId);
        }
        Challenge challenge = optionalChallenge.get();

        // 이미 참여한 경우 예외처리
        boolean alreadyExists = challenge.getParticipants().stream()
                .anyMatch(p -> p.getParticipantId().equals(userId));

        if(alreadyExists) {
            throw new ParticipateException(userId, challengeId);
        }

        Participant participant = Participant.builder().participantId(userId).build();
        challenge.getParticipants().add(participant);
        return challengeRepository.save(challenge);
    }

    public Challenge quiteFromChallenge(UUID userId, UUID challengeId) {
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);
//        if (optionalChallenge.isPresent()) {
//            Challenge challenge = optionalChallenge.get();
//            challenge.getParticipants().removeIf(participant -> participant.getParticipantId().equals(userId));
//            return challengeRepository.save(challenge);
//        } else {
//            throw new IllegalArgumentException("해당 챌린지가 존재하지 않습니다~");
//        }
        if(optionalChallenge.isEmpty()) {
            throw new ChallengeNotFoundException(challengeId);
        }
        Challenge challenge = optionalChallenge.get();

        // 참가 여부 확인 후 예외처리
        boolean isParticipated = challenge.getParticipants().removeIf(participant -> participant.getParticipantId().equals(userId));

        if(!isParticipated) {
            throw new ParticipateException(userId, challengeId, true);
        }
        return challengeRepository.save(challenge);
    }

    public List<Challenge> getAllUserChallenges(UUID userId) {
        return challengeRepository.findByParticipantsParticipantId(userId);
    }

    public Optional<Challenge> getUserChallengeById(UUID userId, UUID challengeId) {
        return challengeRepository.findById(challengeId)
                .filter(challenge -> challenge.getParticipants().stream()
                        .anyMatch(participant -> participant.getParticipantId().equals(userId)));
    }
}
