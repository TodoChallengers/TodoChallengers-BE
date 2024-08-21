package TodoChallengers.BE.challenge.application;

import TodoChallengers.BE.challenge.dto.request.ChecklistRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import TodoChallengers.BE.challenge.entity.ChallengeChecklist;
import TodoChallengers.BE.challenge.entity.Participant;
import TodoChallengers.BE.challenge.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChecklistService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private S3ImageService s3ImageService;

    public Challenge createChecklist(ChecklistRequestDto requestDto){
        String userIdString = requestDto.getUserId();
        String challengeIdString = requestDto.getChecklist().getChallengeId();

        if (userIdString == null || challengeIdString == null) {
            throw new IllegalArgumentException("User ID or Challenge ID cannot be null");
        }

        UUID userId = UUID.fromString(requestDto.getUserId());
        UUID challengeId = UUID.fromString(requestDto.getChecklist().getChallengeId());
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);

        if(optionalChallenge.isPresent()){
            Challenge challenge = optionalChallenge.get();

            // 업로드 할 이미지 파일 가져오기
            MultipartFile imageFile = requestDto.getChecklist().getChecklistPhoto();

            // 이미지 업로드 및 url 반환
            String imageUrl = s3ImageService.upload(imageFile);

            ChallengeChecklist checklist = ChallengeChecklist.builder()
                    .checklistId(UUID.randomUUID())
                    //.checklistDate(requestDto.getChecklist().getChecklistDate())
                    .checklistPhoto(imageUrl)
                    .state("PUBLIC")
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
