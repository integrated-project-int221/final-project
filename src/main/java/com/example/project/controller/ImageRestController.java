package com.example.project.controller;

import com.example.project.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins= {"http://localhost:8081"})
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

    @PostMapping("/upload/{id}")
    public MultipartFile imageUpload(@RequestParam("File") MultipartFile file, @PathVariable Integer id) throws IOException {
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                File imageFile = new File(System.getProperty("user.dir") + FILE_DIRECTORY + productRepositories.findById(id).get().getProdName() + fileType);
                imageFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(imageFile);
                fos.write(file.getBytes());
                fos.close();
                return file;

    }
}
