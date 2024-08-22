package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.UserChallengeService;
import TodoChallengers.BE.challenge.dto.request.UserChallengeRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserChallengeController {
    @Autowired
    private UserChallengeService userChallengeService;

    @PostMapping("/challenge")
    public Challenge participateChallenge(@RequestBody UserChallengeRequestDto requestDto) {
        UUID userId = requestDto.getUserId();
        UUID challengeId = requestDto.getChallengeId();
        return userChallengeService.participateInChallenge(userId,challengeId);
    }

    @DeleteMapping("/challenge")
    public Challenge quiteChallenge(@RequestBody UserChallengeRequestDto requestDto) {
        UUID userId = requestDto.getUserId();
        UUID challengeId = requestDto.getChallengeId();
        return userChallengeService.quiteFromChallenge(userId,challengeId);
    }

    @GetMapping("/challenge/{userId}")
    public List<Challenge> getChallengeByUserId(@PathVariable UUID userId) {
        return userChallengeService.getAllUserChallenges(userId);
    }

    @GetMapping("/challenge/{userId}/{challengeId}")
    public Optional<Challenge> getUserChallengeById(@PathVariable UUID userId, @PathVariable UUID challengeId) {
        return userChallengeService.getUserChallengeById(userId, challengeId);
    }
}
