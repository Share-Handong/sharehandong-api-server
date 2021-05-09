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
    private String content;
    private Integer catego;
    private Integer state;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ShareEntity toEntity(){
        ShareEntity shareEntity = ShareEntity.builder()
                .idx(idx)
                .writer(writer)
                .title(title)
                .content(content)
                .catego(catego)
                .state(state)
                .build();
        return shareEntity;
    }

    @Builder
    public ShareDto(Long idx, String title, String content, Long writer, LocalDateTime createdDate, LocalDateTime modifiedDate,
                    Integer catego, Integer state) {
        this.idx = idx;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.catego = catego;
        this.state = state;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}