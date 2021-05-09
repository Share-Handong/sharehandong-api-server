package sharehandong.sharehandongapiserver.api.v1.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {

    UserEntity findByEmailAndPw(String email, String pw);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}