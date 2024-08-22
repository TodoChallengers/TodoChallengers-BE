package TodoChallengers.BE.challenge.application;

import TodoChallengers.BE.challenge.dto.request.DeleteReactionRequestDto;
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
        UUID challengeId = reactionRequestDto.getChallengeId();
        UUID checklistId = reactionRequestDto.getChecklistId();
        UUID userId = reactionRequestDto.getUserId();

        // Challenge 조회
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);

        if (optionalChallenge.isEmpty()) {
            throw new IllegalArgumentException("Challenge not found");
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

    // reaction 삭제
    public void deleteReaction(DeleteReactionRequestDto requestDto){
        UUID challengeId = requestDto.getChallengeId();
        UUID checklistId = requestDto.getChecklistId();
        UUID userId = requestDto.getUserId();
        UUID reactionId = requestDto.getReactionId();

        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);
        if (optionalChallenge.isEmpty()) {
            throw new IllegalArgumentException("챌린지가 존재하지 않습니다~");
        }
        Challenge challenge = optionalChallenge.get();

        boolean reactionFound = false;
        for(Participant participant : challenge.getParticipants()) {
            for(ChallengeChecklist checklist : participant.getChallengeChecklist()) {
                if(checklist.getChecklistId().equals(checklistId)) {
                    Optional<Reaction> deleteReaction = checklist.getReaction().stream()
                            .filter(reaction -> reaction.getReactionId().equals(reactionId)&&reaction.getReactionGiverId().equals(userId))
                            .findFirst();
                    if (deleteReaction.isPresent()) {
                        checklist.getReaction().remove(deleteReaction.get());
                        reactionFound = true;
                        break;
                    }
                }
            }
            if (reactionFound) break;
        }
        if (reactionFound) {
            challengeRepository.save(challenge);
        } else {
            throw new IllegalArgumentException("리액션이 없거나 해당 사용자는 해당 리액션을 준 사용자가 아닙니다~");
        }
    }
}
