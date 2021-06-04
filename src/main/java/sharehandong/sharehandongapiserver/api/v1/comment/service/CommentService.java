package sharehandong.sharehandongapiserver.api.v1.comment.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentDto;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentRequestDto;
import sharehandong.sharehandongapiserver.api.v1.comment.repository.CommentRepository;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.ShareRepository;
import sharehandong.sharehandongapiserver.api.v1.share.dto.BoardDto;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Getter
@Service
public class CommentService {
    private CommentRepository commentRepository;
    //private final ShareRepository shareRepository;
//    public CommentService(CommentRepository commentRepository, ShareRepository shareRepository) {
//        this.commentRepository = commentRepository;
//        //this.shareRepository = shareRepository;
//    }

    @Transactional
    public void saveComment(/*Long userIdx,*/ Long itemIdx, CommentRequestDto commentRequestDto) {//ShareEntity shareEntity,, Account account

        Comment comment = Comment.builder().content(commentRequestDto.getComment()).build();
        comment.setItemIdx(itemIdx);
        //comment.setUserIdx(userIdx);
        Comment newComment = commentRepository.save(comment);
        //shareEntity.addComment(newComment);
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
    public void deleteComment(Long itemIdx,Long commentId) {//Long postId, , Long accountId
        Comment deleteComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

//        ShareEntity shareEntity = shareRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
//        );
//        Account account = accountRepository.findById(accountId).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );

        //shareEntity.deleteComment(deleteComment);
        //account.deleteComment(deleteComment);
        commentRepository.deleteById(deleteComment.getIdx());

    }

    @Transactional
    public List<CommentDto> getCommentList(Long itemIdx) {
        List<Comment> commentList = commentRepository.findByItemIdx(itemIdx);
//        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();

        for(Comment comment : commentList) {
            CommentDto commentDto = CommentDto.builder()
                    .idx(comment.getIdx())
                    .itemIdx(comment.getItemIdx())
                    .userIdx(comment.getUserIdx())
                    .content(comment.getContent())
                    .del(comment.getDel())
                    .c_date(comment.getCDate())
                    .build();
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    @Transactional
    public CommentDto getCommend(Long id) {
        Optional<Comment> commentWrapper = commentRepository.findById(id);
        Comment comment = commentWrapper.get();

        CommentDto commentDto = CommentDto.builder()
                .idx(comment.getIdx())
                .itemIdx(comment.getItemIdx())
                .userIdx(comment.getUserIdx())
                .content(comment.getContent())
                .del(comment.getDel())
                .c_date(comment.getCDate())
                .build();

        return commentDto;
    }
}

