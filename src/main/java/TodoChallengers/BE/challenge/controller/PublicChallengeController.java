package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.PublicChallengeService;
import TodoChallengers.BE.challenge.application.UserChallengeService;
import TodoChallengers.BE.challenge.dto.request.PublicChallengeRequestDto;
import TodoChallengers.BE.challenge.dto.request.UserChallengeRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import TodoChallengers.BE.challenge.entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/public")
public class PublicChallengeController {
    @Autowired
    private PublicChallengeService publicChallengeService;

//    @Autowired
//    private UserChallengeService userChallengeService;

    /*
    * 현재 모집 중인 챌린지 관련
    */

    @PostMapping("/challenge")
    public Challenge createChallenge(@RequestBody PublicChallengeRequestDto requestDto) {
        UUID leaderId = requestDto.getChallengeLeaderId();

        Challenge challenge = Challenge.builder()
                .id(UUID.randomUUID()) // 새로운 ID를 자동으로 생성
                .challengeName(requestDto.getChallengeName())
                .start(requestDto.getStart())
                .end(requestDto.getEnd())
                .category(requestDto.getCategory())
                .state("PUBLIC")
                .challengeLeaderId(leaderId)
                .participants(new HashSet<>())
                .build();
        challenge.getParticipants().add(Participant.builder().participantId(leaderId).build());

        return publicChallengeService.saveChallenge(challenge);
    }

    @GetMapping("/challenge")
    public List<Challenge> getAllChallenges() {
        return publicChallengeService.getAllPublicChallenges();
    }

    @GetMapping("/challenge/{id}")
    public Optional<Challenge> getChallenge(@PathVariable UUID id) {
        return publicChallengeService.getChallengeById(id);
    }

    @PutMapping("/challenge/{id}")
    public Challenge updateChallenge(@PathVariable UUID id, @RequestBody PublicChallengeRequestDto requestDto) {
        Challenge challenge = Challenge.builder()
                .id(id)
                .challengeName(requestDto.getChallengeName())
                .start(requestDto.getStart())
                .end(requestDto.getEnd())
                .category(requestDto.getCategory())
                .state(requestDto.getState())
                .challengeLeaderId(requestDto.getChallengeLeaderId())
                .build();

        return publicChallengeService.updateChallenge(id, challenge);
    }

    @DeleteMapping("/challenge/{id}")
    public void deleteChallenge(@PathVariable UUID id) {
        publicChallengeService.deleteChallenge(id);
    }

    /*
    * 모집 중인 챌린지에 사용자 들어가기
    */
//    @PostMapping("/user/challenge")
//    public Challenge participateChallenge(@RequestBody UserChallengeRequestDto requestDto) {
//        UUID userId = UUID.fromString(requestDto.getUserId());
//        UUID challengeId = UUID.fromString(requestDto.getChallengeId());
//        return userChallengeService.participateInChallenge(userId,challengeId);
//    }
//
//    @DeleteMapping("/user/challenge")
//    public Challenge quiteChallenge(@RequestBody UserChallengeRequestDto requestDto) {
//        UUID userId = UUID.fromString(requestDto.getUserId());
//        UUID challengeId = UUID.fromString(requestDto.getChallengeId());
//        return userChallengeService.quiteFromChallenge(userId,challengeId);
//    }
//
//    @GetMapping("/user/challenge/{userId}")
//    public List<Challenge> getChallengeByUserId(@PathVariable UUID userId) {
//        return userChallengeService.getAllUserChallenges(userId);
//    }
}
