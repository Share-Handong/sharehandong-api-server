package sharehandong.sharehandongapiserver.api.v1.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.repository.CreateCommentForm;
import sharehandong.sharehandongapiserver.api.v1.comment.dto.CommentDto;
import sharehandong.sharehandongapiserver.api.v1.comment.service.CommentService;
import sharehandong.sharehandongapiserver.api.v1.share.domain.entity.ShareEntity;
import sharehandong.sharehandongapiserver.api.v1.share.dto.BoardDto;
import sharehandong.sharehandongapiserver.api.v1.share.dto.FileDto;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;
import sharehandong.sharehandongapiserver.api.v1.share.service.BoardService;
import sharehandong.sharehandongapiserver.api.v1.share.service.FileService;
import sharehandong.sharehandongapiserver.api.v1.share.service.ShareService;
import sharehandong.sharehandongapiserver.util.MD5Generator;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/comment/list")
    public String list(Model model) {
        List<CommentDto> commentDtoList = commentService.getCommentList();
        model.addAttribute("commentlist", commentDtoList);
        return "board/commentlist.html";
    }

    @GetMapping("/comment/create")
    public String comment() {
        return "board/comment.html";
    }

    @PostMapping("/comment/create")
    public String write(CommentDto commentDto) {
        try{
            commentService.saveComment(commentDto);
            System.out.println("check here 7");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/comment/list";
    }

    @GetMapping("/comment/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        CommentDto commentDto = commentService.getComment(id);
        model.addAttribute("comment", commentDto);
        return "board/detail.html";
    }

//    @PostMapping("/comment/create")
//    public String createComment(CreateCommentForm createCommentForm,
//                                ) {
//
//        ShareEntity post = ShareService.findById(Long.parseLong(createCommentForm.getPostId()));
//        commentService.createComment(createCommentForm,post);
//
//        long postId=  Long.parseLong(createCommentForm.getPostId());
//
//        return "redirect:/post/read?post="+postId;
//    }

//    @PostMapping("/edit")
//    public String editComment(@RequestParam String comment, @RequestParam String content
//            , HttpSession httpSession) {
//
//        long commentId = Long.parseLong(comment);
//
//        commentService.editComment(commentId, content);
//
//        long postId = commentService.findById(commentId).getPost().getId();
//        return "redirect:/post/read?post="+postId;
//    }

//    @GetMapping("/delete")
//    public String deleteComment(@RequestParam String comment,
//                                HttpSession httpSession) {
//
//        long commentId = Long.parseLong(comment);
//        Comment commentToDelete = commentService.findById(commentId);
//
//        long postId = commentToDelete.getPost().getId();
//        commentService.deleteComment(commentId);
//        return "redirect:/post/read?post="+postId;
//    }
}