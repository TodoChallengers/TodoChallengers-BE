package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.ReactionService;
import TodoChallengers.BE.challenge.dto.request.DeleteReactionRequestDto;
import TodoChallengers.BE.challenge.dto.response.ReactionResponseDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import TodoChallengers.BE.challenge.dto.request.ReactionRequestDto;

import java.util.UUID;

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

    // pathvariable 방식
    @GetMapping("/{challengeId}/{checklistId}/reactions")
    public ResponseEntity<ReactionResponseDto> getReactions(
            @PathVariable UUID challengeId,
            @PathVariable UUID checklistId) {
        ReactionResponseDto responseDto = reactionService.getReactions(challengeId, checklistId);
        return ResponseEntity.ok(responseDto);
    }

    // requestparam 방식
    @GetMapping("/reactions")
    public ResponseEntity<ReactionResponseDto> getChecklistReactions(
            @RequestParam UUID challengeId,
            @RequestParam UUID checklistId) {
        ReactionResponseDto responseDto = reactionService.getReactions(challengeId, checklistId);
        return ResponseEntity.ok(responseDto);
    }
}
