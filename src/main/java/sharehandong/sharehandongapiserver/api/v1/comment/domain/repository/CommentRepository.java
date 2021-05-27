package sharehandong.sharehandongapiserver.api.v1.comment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
