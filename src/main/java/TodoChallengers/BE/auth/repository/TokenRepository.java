package TodoChallengers.BE.auth.repository;

import TodoChallengers.BE.auth.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, Long> {
    Optional<Token> findByKakaoId(String kakaoId);

    @Transactional
    void deleteAllByExpiresAtBefore(LocalDateTime expiresAt);
}
