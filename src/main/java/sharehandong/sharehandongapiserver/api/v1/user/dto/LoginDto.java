package sharehandong.sharehandongapiserver.api.v1.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String email;
    private String pw;
    private String name;
}
