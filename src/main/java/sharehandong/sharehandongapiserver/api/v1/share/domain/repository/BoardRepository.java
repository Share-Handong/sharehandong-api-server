package sharehandong.sharehandongapiserver.api.v1.share.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}