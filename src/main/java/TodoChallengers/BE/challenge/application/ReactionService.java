package TodoChallengers.BE.challenge.application;

import TodoChallengers.BE.challenge.dto.request.ReactionRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import TodoChallengers.BE.challenge.entity.ChallengeChecklist;
import TodoChallengers.BE.challenge.entity.Participant;
import TodoChallengers.BE.challenge.entity.Reaction;
import TodoChallengers.BE.challenge.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReactionService {
    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge addReaction(ReactionRequestDto reactionRequestDto) {
        UUID checklistId = reactionRequestDto.getChecklistId();
        UUID userId = reactionRequestDto.getUserId();

        // Challenge 조회
        Optional<Challenge> optionalChallenge = challengeRepository.findByParticipantsParticipantId(userId).stream()
                .filter(challenge -> challenge.getParticipants().stream()
                        .anyMatch(participant -> participant.getChallengeChecklist().stream()
                                .anyMatch(checklist -> checklist.getChecklistId().equals(checklistId))))
                .findFirst();

        if (optionalChallenge.isEmpty()) {
            throw new IllegalArgumentException("Challenge or Checklist not found");
        }

        Challenge challenge = optionalChallenge.get();

        // Checklist 찾기 및 Reaction 추가
        for (Participant participant : challenge.getParticipants()) {
            for (ChallengeChecklist checklist : participant.getChallengeChecklist()) {
                if (checklist.getChecklistId().equals(checklistId)) {
                    Reaction reaction = Reaction.builder()

                            .reactionId(UUID.randomUUID())
                            .reactionGiverId(userId)
                            .build();

                    if (checklist.getReaction() == null) {
                        checklist.setReaction(new HashSet<>());
                    }

                    checklist.getReaction().add(reaction);
                    break;
                }
            }
        }

        // 변경된 Challenge 저장
        return challengeRepository.save(challenge);
    }
}
