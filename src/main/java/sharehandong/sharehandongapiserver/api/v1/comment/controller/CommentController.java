package sharehandong.sharehandongapiserver.api.v1.comment.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.comment.repository.CommentRepository;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentRequestDto;
import sharehandong.sharehandongapiserver.api.v1.comment.service.CommentService;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.ShareRepository;
import org.springframework.http.ResponseEntity;


import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;
    private final ShareRepository shareRepository;
    private final CommentRepository commentRepository;

    @PostMapping("/comment/item/{itemIdx}")
    public ResponseEntity saveComment(@PathVariable("itemIdx")  Long itemIdx, /*@AuthenticationPrincipal UserDetailsImpl userDetails,*/ @RequestBody CommentRequestDto commentRequestDto) {
        ShareEntity post = shareRepository.findById(itemIdx).orElse(null);
        if(post == null) {
            return ResponseEntity.badRequest().build();
        }
        commentService.saveComment(commentRequestDto);//post, userDetails.getAccount()
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comment/item/{itemIdx}")
    public List<Comment> getCommentsByRecipeNo(@PathVariable("itemIdx")  Long itemIdx) {

        return commentRepository.findByPostId(itemIdx);

    }

    @PutMapping("/comment/item/{itemIdx}/{commentIdx}")
    public void updateComment(@PathVariable("itemIdx")  Long itemIdx, @PathVariable("commentIdx")  Long commentIdx,
                              @RequestBody String comment) throws Exception {
        commentService.updateComment(commentIdx, comment);
    }
    @DeleteMapping("/comment/item/{itemIdx}/{commentIdx}")
    public void deleteComment(@PathVariable("itemIdx") Long itemIdx, @PathVariable("commentIdx") Long commentId) {//, @AuthenticationPrincipal UserDetailsImpl userDetails
        commentService.deleteComment(itemIdx ,commentId);//postId, , userDetails.getAccount().getId()
    }
}