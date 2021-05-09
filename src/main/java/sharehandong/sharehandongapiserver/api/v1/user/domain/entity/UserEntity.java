package sharehandong.sharehandongapiserver.api.v1.user.domain.entity;

import lombok.*;
import sharehandong.sharehandongapiserver.util.Common;
import sharehandong.sharehandongapiserver.util.UserRole;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shr_h_user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class UserEntity extends Common implements Serializable {
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    private String pw;

    @Setter
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
