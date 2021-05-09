package sharehandong.sharehandongapiserver.api.v1.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sharehandong.sharehandongapiserver.api.v1.user.dto.LoginDto;
import sharehandong.sharehandongapiserver.api.v1.user.service.UserService;
import sharehandong.sharehandongapiserver.util.TokenUtils;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
@Log4j2
public class UserController {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> signUp(@RequestBody final LoginDto loginDto) {
        return userService.isEmailDuplicated(loginDto.getEmail())
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(TokenUtils.generateJwtToken(userService.login(loginDto)));
    }

//    @GetMapping(value = "/list")
//    public ResponseEntity<UserListResponseDTO> findAll() {
//        final UserListResponseDTO userListResponseDTO = UserListResponseDTO.builder().userList(userService.findAll()).build();
//        return ResponseEntity.ok(userListResponseDTO);
//    }

}
