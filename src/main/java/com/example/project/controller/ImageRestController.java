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
        FileInputStream fileInputStream = new FileInputStream(FILE_DIRECTORY + filename);
        byte[] images = fileInputStream.readAllBytes();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(images);
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> imageUpload(@RequestParam("File") MultipartFile file) throws IOException {
//        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        Path path = Paths.get(FILE_DIRECTORY);
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) Files.createDirectories(path);
        if (FILE_DIRECTORY != null) {
            File imageFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
            imageFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(file.getBytes());
            fos.close();
            return new ResponseEntity<>("File upload complete", HttpStatus.OK);
        } else
            return new ResponseEntity<>("File upload fail", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/delete/{filename:.+}")
    public ResponseEntity<Object> deleteImage(@PathVariable("filename") String filename) {
        File deleteFile = new File(FILE_DIRECTORY + filename);
        deleteFile.delete();
        return new ResponseEntity<>("File Delete complete", HttpStatus.OK);
    }

    @PutMapping("/update/{filename:.+}")
    public ResponseEntity<Object> updateImage(@RequestParam("File") MultipartFile file, @PathVariable("filename") String filename) throws IOException {
        if (filename != null) {
            this.deleteImage(filename);
            this.imageUpload(file);
            return new ResponseEntity<>("File update complete", HttpStatus.OK);
        } else
            return new ResponseEntity<>("File upload fail", HttpStatus.BAD_REQUEST);

    }
}
