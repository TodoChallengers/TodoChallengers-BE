package TodoChallengers.BE.challenge.application;

import TodoChallengers.BE.challenge.dto.request.ChecklistRequestDto;
import TodoChallengers.BE.challenge.dto.request.DeleteChecklistRequestDto;
import TodoChallengers.BE.challenge.dto.response.ChallengeChecklistResponseDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import TodoChallengers.BE.challenge.entity.ChallengeChecklist;
import TodoChallengers.BE.challenge.entity.Participant;
import TodoChallengers.BE.challenge.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChecklistService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private S3ImageService s3ImageService;

    public Challenge createChecklist(ChecklistRequestDto requestDto){
        UUID userId = requestDto.getUserId();
        UUID challengeId = requestDto.getChecklist().getChallengeId();

        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);

        if(optionalChallenge.isPresent()){
            Challenge challenge = optionalChallenge.get();

            // 업로드 할 이미지 파일 가져오기
            MultipartFile imageFile = requestDto.getChecklist().getChecklistPhoto();

            // 이미지 업로드 및 url 반환
            String imageUrl = s3ImageService.upload(imageFile);

            ChallengeChecklist checklist = ChallengeChecklist.builder()
                    .checklistId(UUID.randomUUID())
                    .checklistDate(requestDto.getChecklist().getChecklistDate())
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

    public void deleteChecklist(DeleteChecklistRequestDto requestDto) {
        UUID challengeId = requestDto.getChallengeId();
        UUID checklistId = requestDto.getChecklistId();

        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);
        if (optionalChallenge.isPresent()) {
            Challenge challenge = optionalChallenge.get();
            boolean checklistFound = false;

            for (Participant participant : challenge.getParticipants()) {
                for (ChallengeChecklist checklist : participant.getChallengeChecklist()) {
                    if (checklist.getChecklistId().equals(checklistId)) {
                        // s3에서 이미지 삭제
                        s3ImageService.deleteImageFromS3(checklist.getChecklistPhoto());
                        // 해당 participant에서 해당 checklist 삭제
                        participant.getChallengeChecklist().remove(checklist);
                        checklistFound = true;
                        break;
                    }
                }
                if (checklistFound) break;
            }
            if (checklistFound) {
                challengeRepository.save(challenge);
            } else {
                throw new IllegalArgumentException("해당 챌린지에서 해당 체크리스트 없음~");
            }
        } else {
            throw new IllegalArgumentException("존재하지 않는 챌린지~");
        }
    }
    // 특정 체크리스트 id로 조회

    // 특정 챌린지의 오늘 날짜의 모든 참자가의 인증 조회
    public Optional<ChallengeChecklistResponseDto> getTodayCechklistsForChallenge(UUID challengeId){
        Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);

        if(optionalChallenge.isPresent()){
            Challenge challenge = optionalChallenge.get();
            LocalDate today = LocalDate.now();
            List<ChallengeChecklistResponseDto.ParticipantChecklist> participantChecklistList = new ArrayList<>();

            for(Participant participant : challenge.getParticipants()){
                List<ChallengeChecklist> todayChecklists = participant.getChallengeChecklist().stream()
                        .filter(checklist -> checklist.getChecklistDate() != null && checklist.getChecklistDate().isEqual(today))
                        .collect(Collectors.toList());

                if(!todayChecklists.isEmpty()){
                    participantChecklistList.add(new ChallengeChecklistResponseDto.ParticipantChecklist(participant.getParticipantId(), todayChecklists));
                }
            }
            return Optional.of(new ChallengeChecklistResponseDto(
                    challenge.getId(),
                    challenge.getChallengeName(),
                    participantChecklistList
            ));
        } else {
            throw new IllegalArgumentException("존재하지 않는 챌린지~");
        }
    }
}
