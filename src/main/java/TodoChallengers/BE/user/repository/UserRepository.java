package TodoChallengers.BE.user.repository;

import TodoChallengers.BE.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long userId);
    boolean existsById(Long userId);
    Optional<User> findByKakaoId(Long kakaoid);
}
