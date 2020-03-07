package com.dsky.scdemo.userservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author colby.luo
 * @date 2020/3/4 17:48
 */
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public List<String> upload(@RequestPart("files") List<MultipartFile> files) {
        List<String> paths = new ArrayList<>(files.size());
        try {
            for (MultipartFile file : files) {
                String filename = file.getOriginalFilename();
                Path path = Paths.get("D:/下载/" + filename);
                file.transferTo(path);
                paths.add(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }
}
