package TodoChallengers.BE.challenge.controller;

import TodoChallengers.BE.challenge.application.ChecklistService;
import TodoChallengers.BE.challenge.application.S3ImageService;
import TodoChallengers.BE.challenge.dto.request.ChecklistRequestDto;
import TodoChallengers.BE.challenge.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
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
//    private ChecklistService checklistService;
//
//    @PostMapping("/checklist")
//    public Challenge certifyChecklist(@RequestBody ChecklistRequestDto requestDto){
//        return checklistService.createChecklist(requestDto);
//    }
    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @PostMapping(value = "/checklist", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Challenge> createChecklist(
            @RequestParam("userId") String userId,
            @RequestParam("challengeId") String challengeId,
            @RequestParam("checklistDate") String checklistDateStr,
            @RequestParam("file") MultipartFile checklistPhoto) {

        // userId 검증
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        // challengeId 검증
        if (challengeId == null || challengeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Challenge ID cannot be null or empty");
        }

        // file 검증
        if (checklistPhoto == null || checklistPhoto.isEmpty()) {
            throw new IllegalArgumentException("Checklist Photo file cannot be null or empty");
        }

        // checklistDate 검증 및 변환

        // DTO 생성 및 데이터 설정
        ChecklistRequestDto requestDto = new ChecklistRequestDto();
        ChecklistRequestDto.ChecklistDto checklistDto = new ChecklistRequestDto.ChecklistDto();
        checklistDto.setChallengeId(challengeId);
        //checklistDto.setChecklistDate(java.sql.Date.valueOf(checklistDate)); // Date 형식에 맞게 변환
        checklistDto.setChecklistPhoto(checklistPhoto);

        requestDto.setUserId(userId);
        requestDto.setChecklist(checklistDto);

        // 서비스 호출
        Challenge createdChallenge = checklistService.createChecklist(requestDto);

        return ResponseEntity.ok(createdChallenge);
    }

    @Autowired
    private S3ImageService s3ImageService;

    @PostMapping(value = "/test/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = s3ImageService.upload(file);
        return ResponseEntity.ok(fileUrl);
    }
}
