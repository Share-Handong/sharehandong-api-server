package sharehandong.sharehandongapiserver.api.v1.share.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;
import sharehandong.sharehandongapiserver.api.v1.share.service.ShareService;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ShareController {
    private ShareService shareService;
    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }
    @PostMapping("/share")
    public String write(ShareDto shareDto) {
        shareService.savePost(shareDto);

        return "hi";
    }

}
