package springDemo.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class FileService {
    @Autowired
    HttpSession session;

    public void findAllFiles() {
        String path = "src/main/resources/file";
        File[] file = new File(path).listFiles();
        Map<String,Object> map = new HashMap<>();
        if (file != null) {
            for (File f : file) {
                map.put(f.getName(),f.length());
            }
        }
        session.setAttribute("fileList",map);
    }

    public void uploadFile(MultipartFile file) throws IOException {
        String path = "src/main/resources/file";
        File tmpFile = new File(path+"/"+file.getOriginalFilename());
        Path filePath = Paths.get(path, file.getOriginalFilename());
        if (tmpFile.exists()) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
            String newFileName = prefix + "_Repeat" + suffix;
            filePath = Paths.get(path, newFileName);
            Files.write(filePath, file.getBytes());
            session.setAttribute("uploadResult","上传成功!文件名重复，已重命名为"+newFileName);
        }
        else {
            Files.write(filePath, file.getBytes());
            session.setAttribute("uploadResult","上传成功");
        }
    }

    @Autowired
    private HttpServletResponse resp;
    public void downloadFile(String fileName) {
        try {
            File file = new File("src/main/resources/file/"+fileName);
            String filename = file.getName();
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            resp.reset();
            resp.setCharacterEncoding("UTF-8");
            resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));
            resp.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(resp.getOutputStream());
            resp.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
