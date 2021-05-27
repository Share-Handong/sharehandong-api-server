package sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    public void updateComment(String tempComment) {
        this.content = tempComment;
    }
}

