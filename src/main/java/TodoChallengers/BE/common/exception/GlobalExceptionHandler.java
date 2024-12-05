package TodoChallengers.BE.common.exception;

import TodoChallengers.BE.challenge.exception.ChallengeNotFoundException;
import TodoChallengers.BE.challenge.exception.ParticipateException;
import TodoChallengers.BE.common.util.ApiResponse;
import TodoChallengers.BE.user.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ApiResponse<Void> handleUserException(UserException e) {
        log.info("UserException: {}", e.getMessage());
        return ApiResponse.fail(e.getResponseCode(), e.getMessage());
    }

    @ExceptionHandler(ChallengeNotFoundException.class)
    public ResponseEntity<Object> handleChallengeNotFoundException(ChallengeNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParticipateException.class)
    public ResponseEntity<Object> handleParticipateException(ParticipateException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
