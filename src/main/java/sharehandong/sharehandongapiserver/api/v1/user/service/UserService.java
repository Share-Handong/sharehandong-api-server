package sharehandong.sharehandongapiserver.api.v1.user.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.UserEntity;
import sharehandong.sharehandongapiserver.api.v1.user.domain.repository.UserRepository;
import sharehandong.sharehandongapiserver.api.v1.user.dto.LoginDto;
import sharehandong.sharehandongapiserver.util.UserRole;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserEntity signUp(final LoginDto loginDto) {
        final UserEntity user = UserEntity.builder()
                .email(loginDto.getEmail())
                .pw(passwordEncoder.encode(loginDto.getPw()))
                .role(UserRole.ROLE_USER)
                .userName(loginDto.getName())
                .isEnable(true)
                .build();

        return userRepository.save(user);
    }

    public boolean isEmailDuplicated(final String email) {
        return userRepository.existsByEmail(email);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

}