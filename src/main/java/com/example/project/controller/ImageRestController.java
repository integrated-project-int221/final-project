package com.example.project.controller;

import com.example.project.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;



@RestController
@RequestMapping("/images")
@CrossOrigin(origins= "http://localhost:8081")
public class ImageRestController {
    @Autowired
    ProductRepositories productRepositories;

    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;


    @GetMapping("/get/{filename:.+}")
    public ResponseEntity<byte[]> getImages(@PathVariable("filename") String filename) throws IOException {
        System.out.println(filename);
        FileInputStream fileInputStream = new FileInputStream("images\\" + filename);
        byte[] images = fileInputStream.readAllBytes();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(images);
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> imageUpload(@RequestParam("File") MultipartFile file)  throws IOException {
//        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        Path path = Paths.get(FILE_DIRECTORY);
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) Files.createDirectories(path);
                File imageFile = new File( FILE_DIRECTORY + file.getOriginalFilename() );
                imageFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(file.getBytes());
                fos.close();
                return new ResponseEntity<>("File upload complete", HttpStatus.OK);
    }
}
