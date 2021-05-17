package sharehandong.sharehandongapiserver.api.v1.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sharehandong.sharehandongapiserver.api.v1.user.dto.LoginDto;
import sharehandong.sharehandongapiserver.api.v1.user.service.UserService;
import sharehandong.sharehandongapiserver.util.TokenUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user")
@Log4j2
public class UserController {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody final LoginDto loginDto) {
        String email = loginDto.getEmail();
        String pw = loginDto.getPw();
        String name = loginDto.getName();
        Map<String, Object> result = new HashMap<>();

        if (userService.isEmailDuplicated(email)) {
            result.put("email", email);
            result.put("googleId", pw);
            result.put("name", name);
            return ResponseEntity.ok(result);
        } else {
            result.put("email", email);
            result.put("googleId", pw);
            result.put("name", name);
            result.put("token", TokenUtils.generateJwtToken(userService.signUp(loginDto)));
            return  ResponseEntity.ok(result);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login (@RequestBody final LoginDto loginDto,
                                                      final HttpServletResponse response) {
        System.out.println("hihi" + response.getHeader("Authorization"));
        return ResponseEntity.ok("login");

    }

    @PostMapping(value = "/login/check")
    public ResponseEntity<Map<String, Object>> loginCheck (@RequestBody final String header) {

        return ResponseEntity.ok(null);

    }

//        return userService.isEmailDuplicated(loginDto.getEmail())
////                ? ResponseEntity.badRequest().build()
//                ? ResponseEntity.ok("duplicate")
//                : ResponseEntity.ok(TokenUtils.generateJwtToken(userService.login(loginDto)));
//    }

//    @GetMapping(value = "/list")
//    public ResponseEntity<UserListResponseDTO> findAll() {
//        final UserListResponseDTO userListResponseDTO = UserListResponseDTO.builder().userList(userService.findAll()).build();
//        return ResponseEntity.ok(userListResponseDTO);
//    }

}
