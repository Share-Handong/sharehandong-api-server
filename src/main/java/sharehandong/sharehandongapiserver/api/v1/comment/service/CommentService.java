package sharehandong.sharehandongapiserver.api.v1.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.repository.CommentRepository;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.repository.CreateCommentForm;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentDto;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentRequestDto;
import sharehandong.sharehandongapiserver.api.v1.comment.repository.CommentRepository;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.BoardRepository;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.ShareRepository;
import sharehandong.sharehandongapiserver.api.v1.share.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private final ShareRepository shareRepository;
    public CommentService(CommentRepository commentRepository, ShareRepository shareRepository) {
        this.commentRepository = commentRepository;
        this.shareRepository = shareRepository;
    }

    @Transactional
    public void saveComment(ShareEntity shareEntity, CommentRequestDto commentRequestDto) {//, Account account

        Comment comment = Comment.builder().content(commentRequestDto.getComment()).build();
        Comment newComment = commentRepository.save(comment);
        shareEntity.addComment(newComment);
        //account.addComment(newComment);
    }

    @Transactional
    public void updateComment(Long commentId, String stringComment) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        comment.updateComment(stringComment);
    }
    @Transactional
    public void deleteComment(Long postId, Long commentId) {//, Long accountId
        Comment deleteComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        ShareEntity shareEntity = shareRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
//        Account account = accountRepository.findById(accountId).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );

        shareEntity.deleteComment(deleteComment);
        //account.deleteComment(deleteComment);
        commentRepository.deleteById(deleteComment.getIdx());

    }
}

