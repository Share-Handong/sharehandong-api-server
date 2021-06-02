package sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity(name = "share_h_item_comment")
@Entity
@Table(name = "share_h_item_comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue
    @Column(name="idx")
    private Long idx;

    @Column(name="item_idx")
    private Long itemIdx;

    @Column(name="user_idx")
    private Long userIdx;

    @Lob
    @Column(name="content")
    private String content;

    @CreatedDate
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name="c_date")
    private LocalDateTime cDate;

    @Column(name="del")
    private int del;

    @Builder
    public Comment(Long idx, Long itemIdx, Long userIdx, String content,
                   LocalDateTime cDate, int del) {
        this.idx = idx;
        this.itemIdx = itemIdx;
        this.userIdx = userIdx;
        this.content = content;
        this.cDate = cDate;
        this.del = del;
    }

    public void updateComment(String tempComment) {
        this.content = tempComment;
    }
}

