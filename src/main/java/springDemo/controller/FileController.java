package springDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springDemo.service.FileService;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/file")
    public String toFile() {
        fileService.findAllFiles();
        return "file";
    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        fileService.uploadFile(file);
        return "redirect:/file";
    }

    @RequestMapping("/download")
    public String download(@RequestParam String filename) {
        fileService.downloadFile(filename);
        return "redirect:/file";
    }
}
