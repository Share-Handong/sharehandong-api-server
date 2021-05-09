package sharehandong.sharehandongapiserver.api.v1.user.dto;

import lombok.Getter;

@Getter
public class LoginDto {
    private String email;
    private String pw;
}
