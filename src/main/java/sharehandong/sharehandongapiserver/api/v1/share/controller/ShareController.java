package sharehandong.sharehandongapiserver.api.v1.share.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sharehandong.sharehandongapiserver.api.v1.share.dto.BoardDto;
import sharehandong.sharehandongapiserver.api.v1.share.dto.FileDto;
import sharehandong.sharehandongapiserver.api.v1.share.dto.ShareDto;
import sharehandong.sharehandongapiserver.api.v1.share.service.BoardService;
import sharehandong.sharehandongapiserver.api.v1.share.service.FileService;
import sharehandong.sharehandongapiserver.api.v1.share.service.ShareService;
import sharehandong.sharehandongapiserver.util.MD5Generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping("/share/item") // 등록 post
    public String post() {
        return "board/post.html";
    }

    @PostMapping("/share/item")
    public String write(@RequestParam("file") MultipartFile files, ShareDto shareDto) {
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));
            FileDto fileDto = new FileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);

            Long fileId = fileService.saveFile(fileDto);
            shareService.savePost(shareDto);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }


    @GetMapping("/share/item/{id}") //글 한개 볼때 return
    public String detail(@PathVariable("id") Long id, Model model) {
        ShareDto shareDto = shareService.getPost(id);
        model.addAttribute("post", shareDto);
        return "board/detail.html";
    }

//    @GetMapping("/share/item") //글 리스트 return
//    public String shareList(Model model) {
//        List<ShareDto> shareDtoList = shareService.getBoardList();
//        model.addAttribute("postList", shareDtoList);
//        return "board/list.html";
//    }

    @PutMapping("/share/item/{id}") //글 삭제 delete
    public String update(ShareDto shareDto) {
        shareService.savePost(shareDto);
        return "redirect:/";
    }

    @PutMapping("/item/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        ShareDto shareDto = shareService.getPost(id);
        model.addAttribute("post", shareDto);
        return "board/edit.html";
    }


    @DeleteMapping("/tiem/{id}")
    public String delete(@PathVariable("id") Long id) {
        shareService.deletePost(id);
        return "redirect:/";
    }


//    @PostMapping("/post")
//    public String write(@RequestParam("file") MultipartFile files, ShareDto shareDto) {
//        try {
//            String origFilename = files.getOriginalFilename();
//            String filename = new MD5Generator(origFilename).toString();
//            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
//            System.out.println("check here 1");
//            String savePath = System.getProperty("user.dir") + "\\files";
//            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
//            if (!new File(savePath).exists()) {
//                try{
//                    new File(savePath).mkdir();
//                }
//                catch(Exception e){
//                    e.getStackTrace();
//                }
//            }
//
//            String filePath = savePath + "\\" + filename;
//            files.transferTo(new File(filePath));
//
//            FileDto fileDto = new FileDto();
//            fileDto.setOrigFilename(origFilename);
//            fileDto.setFilename(filename);
//            fileDto.setFilePath(filePath);
//            Long fileId = fileService.saveFile(fileDto);
//            shareDto.setFile_id(fileId);
//            shareService.savePost(shareDto);
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/";
//    }


//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
//        FileDto fileDto = fileService.getFile(fileId);
//        Path path = Paths.get(fileDto.getFilePath());
//        Resource resource = new InputStreamResource(Files.newInputStream(path));
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
//                .body(resource);
//    }
//


//    @GetMapping("/post")
//    public String write() {
//        return "board/write.html";
//    }
//
//
//    @PostMapping("/share/share_form")
//    public String write(ShareDto shareDto) {
//        shareService.savePost(shareDto);
//
//        return "hi";
//    }






}
