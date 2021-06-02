package sharehandong.sharehandongapiserver.api.v1.share.dto;
import lombok.*;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private Long file_id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public BoardEntity toEntity() {
        BoardEntity build = BoardEntity.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .file_id(file_id)
                .created_date(LocalDateTime.now())
                .modified_date(LocalDateTime.now())
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, Long file_id, String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.file_id = file_id;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.file_id = id;
    }
}