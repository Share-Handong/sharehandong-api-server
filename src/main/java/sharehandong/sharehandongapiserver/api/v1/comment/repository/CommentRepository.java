package sharehandong.sharehandongapiserver.api.v1.comment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    List<Comment> findAllByPostId(Long id);
}

