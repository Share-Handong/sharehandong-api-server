package sharehandong.sharehandongapiserver.api.v1.share.domain.entity;

import lombok.AccessLevel;
import lombok.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ShareEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long writer;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Integer catego;

    @Column
    private Integer state;


    @Builder
    public ShareEntity(Long idx, String title, String content, Long writer,
                       Integer catego, Integer state) {
        this.idx = idx;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.catego = catego;
        this.state = state;
    }
}