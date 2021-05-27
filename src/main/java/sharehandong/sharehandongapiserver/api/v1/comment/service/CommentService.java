package sharehandong.sharehandongapiserver.api.v1.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.repository.CommentRepository;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.repository.CreateCommentForm;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentDto;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.BoardEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.BoardRepository;
import sharehandong.sharehandongapiserver.api.v1.share.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Long saveComment(CommentDto commentDto) {
        return commentRepository.save(commentDto.toEntity()).getIdx();
    }

    @Transactional
    public List<CommentDto> getCommentList() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();

        for(Comment comment : commentList) {
            CommentDto commentDto = CommentDto.builder()
                    .idx(comment.getIdx())
                    .item_idx(comment.getItem_idx())
                    .user_idx(comment.getUser_idx())
                    .content(comment.getContent())
                    .del(comment.getDel())
                    .c_date(comment.getC_date())
                    .build();
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    @Transactional
    public CommentDto getComment(Long id) {
        Comment comment = commentRepository.findById(id).get();

        CommentDto commentDto = CommentDto.builder()
                .idx(comment.getIdx())
                .item_idx(comment.getItem_idx())
                .user_idx(comment.getUser_idx())
                .content(comment.getContent())
                .del(comment.getDel())
                .c_date(comment.getC_date())
                .build();
        return commentDto;
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

