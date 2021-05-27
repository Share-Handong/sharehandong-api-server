package sharehandong.sharehandongapiserver.api.v1.comment.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.comment.repository.CommentRepository;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentRequestDto;
import sharehandong.sharehandongapiserver.api.v1.comment.service.CommentService;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.ShareRepository;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final ShareRepository shareRepository;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService, ShareRepository shareRepository, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.shareRepository = shareRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/post/{postId}/comment")
    public List<Comment> getCommentsByRecipeNo(@PathVariable Long postId) {

        return commentRepository.findByPostId(postId);

    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity saveComment(@PathVariable Long postId, /*@AuthenticationPrincipal UserDetailsImpl userDetails,*/ @RequestBody CommentRequestDto commentRequestDto) {
        ShareEntity post = shareRepository.findById(postId).orElse(null);
        if(post == null) {
            return ResponseEntity.badRequest().build();
        }
        commentService.saveComment(post, commentRequestDto);//, userDetails.getAccount()
        return ResponseEntity.ok().build();
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public void updateComment(@PathVariable Long postId, @PathVariable Long commentId,
                              @RequestBody String comment) throws Exception {
        commentService.updateComment(commentId, comment);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {//, @AuthenticationPrincipal UserDetailsImpl userDetails
        commentService.deleteComment(postId, commentId);//, userDetails.getAccount().getId()
    }
}