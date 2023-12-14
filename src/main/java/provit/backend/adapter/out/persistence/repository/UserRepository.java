package provit.backend.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import provit.backend.adapter.out.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
