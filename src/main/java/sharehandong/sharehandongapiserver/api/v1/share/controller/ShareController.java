package sharehandong.sharehandongapiserver.api.v1.share.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ItemDto;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;
import sharehandong.sharehandongapiserver.api.v1.share.service.FileService;
import sharehandong.sharehandongapiserver.api.v1.share.service.ShareService;
import sharehandong.sharehandongapiserver.api.v1.user.domain.entity.MyUserDetails;

import java.net.URLDecoder;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ShareController {
    private final FileService fileService;
    private final ShareService shareService;

//    public ShareController(ShareService shareService, FileService fileService) {
//        this.shareService = shareService;
//        this.fileService = fileService;
//    }

    @PostMapping("/share/item")// 글 등록 post
    public ResponseEntity<?> write(@RequestParam("userName") String userName, @RequestBody ShareDto shareDto) {
        return ResponseEntity.ok( shareService.savePost(shareDto, userName));
    }


    @GetMapping("/share/myitem") //내가 쓴 글
    public ResponseEntity<?>  getMyShareList(@RequestParam("userName") String userName) {
        List<ShareDto> shareDtoList =  shareService.getMyShareList(userName);
//        model.addAttribute("postList", shareDtoList);
//        model.addAttribute("post", shareDto);
        return ResponseEntity.ok(shareDtoList);
    }

    @GetMapping("/share/item/{id}") //글 한개 볼때 return
    public ResponseEntity<?>  detail(@PathVariable("id") Long id) {
        ShareDto shareDto = shareService.getPost(id);
//        model.addAttribute("post", shareDto);
        return ResponseEntity.ok(shareDto);
    }

    @GetMapping("/share/item")// 글 리스트 볼때
    public ResponseEntity<?> list(Model model) {
        List<ShareDto> shareDtoList = shareService.getShareList();
        model.addAttribute("postList", shareDtoList);
        return ResponseEntity.ok(shareDtoList);
    }


    @DeleteMapping("/share/item/{id}") //글 삭제
    public ResponseEntity delete(@PathVariable("id") Long id) {
        shareService.deletePost(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/share/item/{id}") //글 업데이트
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody ShareDto shareDto) {

        return ResponseEntity.ok(shareService.updatePost(id,shareDto));
    }

}
