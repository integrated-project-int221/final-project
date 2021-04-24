package com.example.project.contrller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/images")
public class ImageRestController {

    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;

    @GetMapping("/get/{filename:.+}")
    public ResponseEntity<byte[]> getImages(@PathVariable("filename")String filename) throws IOException {
        System.out.println(filename);
        FileInputStream fileInputStream = new FileInputStream("images\\" + filename);
        byte[] images = fileInputStream.readAllBytes();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(images);
    }

    @PostMapping("/upload")
    public MultipartFile imageUpload(@RequestParam("File")MultipartFile file) throws IOException {
        File imageFile = new File(System.getProperty("user.dir")+FILE_DIRECTORY+file.getOriginalFilename());
        imageFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(imageFile);
        fos.write(file.getBytes());
        fos.close();
        return file;
    }
}
