package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.ChecklistService;
import TodoChallengers.BE.challenge.dto.request.ChecklistRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ChecklistController {
    @Autowired
    private ChecklistService checklistService;

    @PostMapping("/checklist")
    public Challenge certifyChecklist(@RequestBody ChecklistRequestDto requestDto){
        return checklistService.createChecklist(requestDto);
    }
}
