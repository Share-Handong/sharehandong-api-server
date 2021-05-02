package sharehandong.sharehandongapiserver.api.v1.share.dto;

import lombok.*;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ShareDto {
    private Long idx;
    private Long writer;
    private String title;
    private String desc;
    private Integer catego;
    private Integer state;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ShareEntity toEntity(){
        ShareEntity shareEntity = ShareEntity.builder()
                .idx(idx)
                .writer(writer)
                .title(title)
                .desc(desc)
                .catego(catego)
                .state(state)
                .build();
        return shareEntity;
    }

    @Builder
    public ShareDto(Long idx, String title, String desc, Long writer, LocalDateTime createdDate, LocalDateTime modifiedDate,
                    Integer catego, Integer state) {
        this.idx = idx;
        this.writer = writer;
        this.title = title;
        this.desc = desc;
        this.catego = catego;
        this.state = state;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}