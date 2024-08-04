package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.PublicChallengeService;
import TodoChallengers.BE.challenge.application.UserChallengeService;
import TodoChallengers.BE.challenge.dto.request.PublicChallengeRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ChallengeController {
    @Autowired
    private PublicChallengeService publicChallengeService;

    @Autowired
    private UserChallengeService userChallengeService;

    @PostMapping("/challenge")
    public Challenge createChallenge(@RequestBody PublicChallengeRequestDto requestDto) {
        Challenge challenge = Challenge.builder()
                .id(UUID.randomUUID()) // 새로운 ID를 자동으로 생성
                .challengeName(requestDto.getChallengeName())
                .start(requestDto.getStart())
                .end(requestDto.getEnd())
                .category(requestDto.getCategory())
                .state(requestDto.getState())
                .challengeLeaderId(UUID.fromString(requestDto.getChallengeLeaderId()))
                .build();

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

    @DeleteMapping("/challenge/{id}")
    public void deleteChallenge(@PathVariable UUID id) {
        publicChallengeService.deleteChallenge(id);
    }
}
