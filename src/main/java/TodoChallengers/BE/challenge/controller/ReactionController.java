package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.ReactionService;
import TodoChallengers.BE.challenge.dto.request.DeleteReactionRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import TodoChallengers.BE.challenge.dto.request.ReactionRequestDto;

@RestController
@RequestMapping("/api/checklist")
public class ReactionController {
    @Autowired
    private ReactionService reactionService;

    @PostMapping("/reaction")
    public ResponseEntity<Challenge> addReaction(@RequestBody ReactionRequestDto requestDto) {
        Challenge updatedChallenge = reactionService.addReaction(requestDto);
        return ResponseEntity.ok(updatedChallenge);
    }

    @DeleteMapping("/reaction")
    public ResponseEntity<Challenge> deleteReaction(@RequestBody DeleteReactionRequestDto requestDto) {
        reactionService.deleteReaction(requestDto);
        return ResponseEntity.ok(null);
    }
}
