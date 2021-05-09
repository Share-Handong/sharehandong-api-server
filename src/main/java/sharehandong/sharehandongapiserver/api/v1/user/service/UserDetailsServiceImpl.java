package sharehandong.sharehandongapiserver.api.v1.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.MyUserDetails;
import sharehandong.sharehandongapiserver.api.v1.user.domain.repository.UserRepository;
import sharehandong.sharehandongapiserver.exception.UserNotFoundException;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .map(u -> new MyUserDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue()))))
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}