package TodoChallengers.BE.challenge.application;

import TodoChallengers.BE.challenge.dto.request.ChecklistRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import TodoChallengers.BE.challenge.entity.ChallengeChecklist;
import TodoChallengers.BE.challenge.entity.Participant;
import TodoChallengers.BE.challenge.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChecklistService {
    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge createChecklist(ChecklistRequestDto requestDto){
        UUID userId = UUID.fromString(requestDto.getUserId());
        UUID challengeId = UUID.fromString(requestDto.getChecklist().getChallengeId());
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);
        if(optionalChallenge.isPresent()){
            Challenge challenge = optionalChallenge.get();

            ChallengeChecklist checklist = ChallengeChecklist.builder()
                    .checklistId(UUID.randomUUID())
                    .checklistDate(requestDto.getChecklist().getChecklistDate())
                    .checklistPhoto("asdf")
                    .build();

            for(Participant participant : challenge.getParticipants()){
                if (participant.getParticipantId().equals(userId)) {
                    participant.addChecklist(checklist);
                    break;
                }
            }
            return challengeRepository.save(challenge);
        } else{
            throw new IllegalArgumentException("챌린지 ㄴㄴ");
        }
    }
}
