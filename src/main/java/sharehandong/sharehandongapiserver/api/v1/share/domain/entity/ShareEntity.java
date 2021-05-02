package sharehandong.sharehandongapiserver.api.v1.share.domain.entity;

import lombok.AccessLevel;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "shr_h_item")
public class ShareEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long writer;

    @Column
    private String title;

    @Column
    private String desc;

    @Column
    private Integer catego;

    @Column
    private Integer state;

    @Builder
    public ShareEntity(Long idx, String title, String desc, Long writer,
                       Integer catego, Integer state) {
        this.idx = idx;
        this.writer = writer;
        this.title = title;
        this.desc = desc;
        this.catego = catego;
        this.state = state;
    }
}