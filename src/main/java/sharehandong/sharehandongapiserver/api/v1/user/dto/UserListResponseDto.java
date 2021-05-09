package sharehandong.sharehandongapiserver.api.v1.user.dto;

import lombok.Builder;
import lombok.Getter;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.UserEntity;

import java.util.List;

@Getter
@Builder
public class UserListResponseDto {
    private final List<UserEntity> userList;
}
