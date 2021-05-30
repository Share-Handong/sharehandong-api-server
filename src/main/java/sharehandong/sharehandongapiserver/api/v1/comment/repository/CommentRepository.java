package sharehandong.sharehandongapiserver.api.v1.comment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;

import javax.persistence.EntityManager;
import java.util.List;
@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //List<Comment> findByItemIdx(Long itemIdx);

    List<Comment> findAllByItemIdx(Long itemIdx);
}

