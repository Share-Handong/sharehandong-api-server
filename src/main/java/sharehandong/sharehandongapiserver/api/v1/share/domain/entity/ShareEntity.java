package sharehandong.sharehandongapiserver.api.v1.share.domain.entity;

import lombok.AccessLevel;
import lombok.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="share_entity")
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
public class ShareEntity extends TimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "writer")
    private Long writer;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "catego")
    private Integer catego;

    @Column(name = "state")
    private Integer state;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;


    @Builder
    public ShareEntity(Long idx, String title, String content, Long writer,
                       Integer catego, Integer state,LocalDateTime created_date,LocalDateTime modified_date) {
        this.idx = idx;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.catego = catego;
        this.state = state;
        this.createdDate = created_date;
        this.modifiedDate = modified_date;
    }



    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public Long getWriter() {
        return writer;
    }

    public void setWriter(Long writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCatego() {
        return catego;
    }

    public void setCatego(Integer catego) {
        this.catego = catego;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public LocalDateTime getCreated_date() {
        return createdDate;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.createdDate = created_date;
    }

    public LocalDateTime getModified_date() {
        return modifiedDate;
    }

    public void setModified_date(LocalDateTime modified_date) {
        this.modifiedDate = modified_date;
    }
}