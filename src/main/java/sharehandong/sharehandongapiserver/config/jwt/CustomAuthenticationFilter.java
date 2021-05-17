package sharehandong.sharehandongapiserver.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.UserEntity;
import sharehandong.sharehandongapiserver.api.v1.user.dto.LoginDto;
import sharehandong.sharehandongapiserver.exception.InputNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2 // 로그문 출력
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public CustomAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken authRequest;
        try {
            System.out.println(request.getInputStream());
            final LoginDto user = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            authRequest = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPw());
        } catch (IOException exception) {
            throw new InputNotFoundException();
        }
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
