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
    private Long itemIdx;
    private Long userIdx;
    private String content;
    private LocalDateTime c_date;
    private int del;
    private String userName;

    public Comment toEntity() {
        Comment build = Comment.builder()
                .idx(idx)
                .itemIdx(itemIdx)
                .content(content)
                .userIdx(userIdx)
                .del(del)
                .cDate(LocalDateTime.now())
                .build();
        return build;
    }
    @Builder
    public CommentDto(Long idx, Long itemIdx, Long userIdx, String content, LocalDateTime c_date, int del, String userName) {
        this.idx = idx;
        this.itemIdx = itemIdx;
        this.userIdx = userIdx;
        this.content = content;
        this.c_date = c_date;
        this.del = del;
        this.userName = userName;
    }
}