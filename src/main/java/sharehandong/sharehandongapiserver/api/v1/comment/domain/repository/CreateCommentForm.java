package sharehandong.sharehandongapiserver.api.v1.comment.domain.repository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCommentForm {
    private String content;
    private String postId;
    private String commentId;
}
