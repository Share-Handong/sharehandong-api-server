package sharehandong.sharehandongapiserver.config.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.MyUserDetails;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.UserEntity;
import sharehandong.sharehandongapiserver.util.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) {
        // error 시 getUser => getUserEntity
        final UserEntity user = ((MyUserDetails)authentication.getPrincipal()).getUser();
        final String token = TokenUtils.generateJwtToken(user);
        response.addHeader("Refresh_token", token);
    }
}
