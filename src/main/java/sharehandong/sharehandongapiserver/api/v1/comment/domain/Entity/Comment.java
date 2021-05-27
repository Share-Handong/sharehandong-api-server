package sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue
    @Column(name="idx")
    private Long idx;

    @Column(name="item_idx")
    private Long item_idx;

    @Column(name="user_idx")
    private Long user_idx;

    @Lob
    @Column(name="content")
    private String content;

    @CreatedDate
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name="c_date")
    private LocalDateTime c_date;

    @Column(name="del")
    private int del;

    @Builder
    public Comment(Long idx, Long item_idx, Long user_idx, String content, LocalDateTime c_date, int del) {
        this.idx = idx;
        this.item_idx = item_idx;
        this.user_idx = user_idx;
        this.content = content;
        this.c_date = c_date;
        this.del = del;
    }

}

