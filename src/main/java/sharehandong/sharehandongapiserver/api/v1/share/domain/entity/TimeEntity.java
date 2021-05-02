package sharehandong.sharehandongapiserver.api.v1.share.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
    @CreatedDate
    @Column(updatable = false, columnDefinition="DATETIME")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(columnDefinition="DATETIME")
    private LocalDateTime modifiedDate;

}