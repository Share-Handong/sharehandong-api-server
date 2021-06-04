package sharehandong.sharehandongapiserver.api.v1.comment.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentDto;
import sharehandong.sharehandongapiserver.api.v1.comment.repository.CommentRepository;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentRequestDto;
import sharehandong.sharehandongapiserver.api.v1.comment.service.CommentService;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.domain.repository.ShareRepository;
import org.springframework.http.ResponseEntity;
import sharehandong.sharehandongapiserver.api.v1.share.dto.BoardDto;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.MyUserDetails;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.UserEntity;


import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;
    private final ShareRepository shareRepository;
    private final CommentRepository commentRepository;

    @PostMapping("/comment/item/{itemIdx}")
    public ResponseEntity saveComment(@PathVariable("itemIdx")  Long itemIdx, /*@AuthenticationPrincipal MyUserDetails userDetails,*/ @RequestBody CommentRequestDto commentRequestDto) {
        ShareEntity post = shareRepository.findById(itemIdx).orElse(null);
        if(post == null) {
            System.out.println("post NULL");
            return ResponseEntity.badRequest().build();
        }
        //long userIdx = userDetails.getUserIdx();
        commentService.saveComment(/*userIdx,*/itemIdx,commentRequestDto);//post, userDetails.getAccount()
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/comment/item/{itemIdx}")
//    public List<Comment> getCommentsByRecipeNo(@PathVariable("itemIdx")  Long itemIdx) {
//        List<Comment> l = commentRepository.findAllByItemIdx(itemIdx);
//        System.out.println("DFSDFASDFASDFWQEGWEFDWFDWQGWDGQWDGQ");
//        System.out.println(l);
//        return commentRepository.findAllByItemIdx(itemIdx);
//
//    }

    @GetMapping("/comment/item/{itemIdx}")
    public ResponseEntity<?> list(Model model, @PathVariable("itemIdx")  Long itemIdx) {
        List<CommentDto> commentDtoList = commentService.getCommentList(itemIdx);
        model.addAttribute("commentlist", commentDtoList);
        //return commentRepository.findAllByItemIdx(itemIdx);
        return ResponseEntity.ok(commentDtoList);
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