package sharehandong.sharehandongapiserver.api.v1.share.dto;

import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.UserEntity;

import java.time.LocalDateTime;

public class ItemDto {

    private Long idx;
    private String title;
    private String content;
    private Integer catego;
    private Integer state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userName;

    public ItemDto(ShareDto shareDto, String userName) {
        this.idx = shareDto.getIdx();
        this.title = shareDto.getTitle();
        this.content = shareDto.getContent();
        this.catego = shareDto.getCatego();
        this.state = shareDto.getState();
        this.updatedAt = shareDto.getUpdatedAt();
        this.createdAt = shareDto.getCreatedAt();
        this.userName = userName;
    }

}
