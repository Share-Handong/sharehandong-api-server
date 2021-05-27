package sharehandong.sharehandongapiserver.api.v1.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long idx;
    private Long item_idx;
    private Long user_idx;
    private String content;
    private LocalDateTime c_date;
    private int del;

    public Comment toEntity() {
        Comment build = Comment.builder()
                .idx(idx)
                .item_idx(item_idx)
                .content(content)
                .user_idx(user_idx)
                .del(del)
                .c_date(LocalDateTime.now())
                .build();
        return build;
    }
    @Builder
    public CommentDto(Long idx, Long item_idx, Long user_idx, String content, LocalDateTime c_date, int del) {
        this.idx = idx;
        this.item_idx = item_idx;
        this.user_idx = user_idx;
        this.content = content;
        this.c_date = c_date;
        this.del = del;
    }
}