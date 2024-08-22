package TodoChallengers.BE.challenge.application;

import TodoChallengers.BE.challenge.dto.response.PublicChallengeResponseDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import TodoChallengers.BE.challenge.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PublicChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge saveChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

//    public List<Challenge> getAllPublicChallenges() {
//        return challengeRepository.findByState("PUBLIC");
//    }
    public List<PublicChallengeResponseDto> getAllPublicChallenges() {
        List<Challenge> challenges = challengeRepository.findByState("PUBLIC");
        return challenges.stream()
                .map(challenge -> new PublicChallengeResponseDto(challenge.getId(), challenge.getChallengeName()))
                .collect(Collectors.toList());
    }

    public Optional<Challenge> getChallengeById(UUID id) {
        return challengeRepository.findById(id);
    }

    public Challenge updateChallenge(UUID id, Challenge challenge) {
        if (challengeRepository.existsById(id)) {
            return challengeRepository.save(challenge);
        } else {
            throw new IllegalArgumentException(id + "의 챌린지가 없음~");
        }
    }

    public void deleteChallenge(UUID challengeId) {
        challengeRepository.deleteById(challengeId);
    }
}
