package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.ChecklistService;
import TodoChallengers.BE.challenge.application.S3ImageService;
import TodoChallengers.BE.challenge.dto.request.ChecklistRequestDto;
import TodoChallengers.BE.challenge.dto.request.DeleteChecklistRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

@RestController
@RequestMapping("/api/user")
public class ChecklistController {
//    @Autowired
//    private S3ImageService s3ImageService;

    @Autowired
    private ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @PostMapping(value = "/checklist", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Challenge> createChecklist(
            @RequestParam("userId") String userId,
            @RequestParam("challengeId") String challengeId,
            @RequestParam("checklistDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("file") MultipartFile checklistPhoto) {

        System.out.println("date!!"+date);

        // DTO 생성 및 데이터 설정
        ChecklistRequestDto requestDto = new ChecklistRequestDto();
        ChecklistRequestDto.ChecklistDto checklistDto = new ChecklistRequestDto.ChecklistDto();
        checklistDto.setChallengeId(challengeId);
        checklistDto.setChecklistDate(java.sql.Date.valueOf(date)); // Date 형식에 맞게 변환
        checklistDto.setChecklistPhoto(checklistPhoto);

        requestDto.setUserId(userId);
        requestDto.setChecklist(checklistDto);

        // 서비스 호출
        Challenge createdChallenge = checklistService.createChecklist(requestDto);

        return ResponseEntity.ok(createdChallenge);
    }

    @DeleteMapping("/checklist")
    public ResponseEntity<Challenge> deleteChecklist(@RequestBody DeleteChecklistRequestDto requestDto){
        checklistService.deleteChecklist(requestDto);
        return ResponseEntity.ok().build();
    }
}
