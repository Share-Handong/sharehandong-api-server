package sharehandong.sharehandongapiserver.api.v1.share.controller;

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
import sharehandong.sharehandongapiserver.api.v1.share.service.BoardService;
import sharehandong.sharehandongapiserver.api.v1.share.service.FileService;
import sharehandong.sharehandongapiserver.util.MD5Generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class BoardController {
    private final FileService fileService;
    private BoardService boardService;
    
    public BoardController(BoardService boardService, FileService fileService) {
        this.boardService = boardService;
        this.fileService = fileService;
    }
    @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post() {
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(@RequestParam("file") MultipartFile files, BoardDto boardDto) {
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            System.out.println("check here 1");
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
            System.out.println("check here 2");
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            System.out.println("check here 3");
            FileDto fileDto = new FileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);
            System.out.println("check here 4");

            Long fileId = fileService.saveFile(fileDto);
            System.out.println("check here 5");
            boardDto.setFile_id(fileId);
            System.out.println("check here 6");
            boardService.savePost(boardDto);
            System.out.println("check here 7");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";

    }

    @PutMapping("/post/edit/{id}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileDto fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);
    }

}