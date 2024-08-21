package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.ReactionService;
import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import TodoChallengers.BE.challenge.dto.request.ReactionRequestDto;

@RestController
@RequestMapping("/api/checklist")
public class ReactionController {
    @Autowired
    private ReactionService reactionService;

    @PostMapping("/reaction/")
    public ResponseEntity<Challenge> addReaction(@RequestBody ReactionRequestDto reactionRequestDto) {
        Challenge updatedChallenge = reactionService.addReaction(reactionRequestDto);
        return ResponseEntity.ok(updatedChallenge);
    }
}
