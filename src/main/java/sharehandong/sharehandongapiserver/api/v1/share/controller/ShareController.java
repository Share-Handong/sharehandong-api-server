package sharehandong.sharehandongapiserver.api.v1.share.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;
import sharehandong.sharehandongapiserver.api.v1.share.service.FileService;
import sharehandong.sharehandongapiserver.api.v1.share.service.ShareService;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class ShareController {
    private final FileService fileService;
    private ShareService shareService;

    public ShareController(ShareService shareService, FileService fileService) {
        this.shareService = shareService;
        this.fileService = fileService;
    }

    @PostMapping("/share/item")// 글 등록 post
    public ResponseEntity<?> write(ShareDto shareDto) {
//        try {
//            shareService.savePost(shareDto);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.ok(shareService.savePost(shareDto));
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
    public ResponseEntity<?> edit(@PathVariable("id") Long id, ShareDto shareDto) {

        return ResponseEntity.ok(shareService.updatePost(id,shareDto));
    }

}
