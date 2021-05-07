package com.example.project.controller;

import com.example.project.model.Products;
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
public class ImageRestController {
    @Autowired
    ProductRepositories productRepositories;


    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;


    @GetMapping("/get/{filename:.+}")
    public ResponseEntity<byte[]> getImages(@PathVariable("filename") String filename) throws IOException {
        try {
            System.out.println(filename);
            FileInputStream fileInputStream = new FileInputStream(FILE_DIRECTORY + filename);
            byte[] images = fileInputStream.readAllBytes();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(images);
        }catch (EOFException e){
            return ResponseEntity.badRequest().body(null);
        }


    }

    @PostMapping("/upload/{productName}")
    public ResponseEntity<Object> imageUpload(@RequestParam("File") MultipartFile file,@PathVariable("productName") String productName ) throws IOException {
        try{
//        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        Path path = Paths.get(FILE_DIRECTORY);
        File dir = new File(FILE_DIRECTORY);
        if (!dir.exists()) Files.createDirectories(path);
            File imageFile = new File(FILE_DIRECTORY + productName + file.getOriginalFilename());
            
//            String imageName = productRepositories.findById(id).get().getProdName() + fileType;
            imageFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(file.getBytes());
            fos.close();
            return new ResponseEntity<>("File upload complete", HttpStatus.OK);
        }catch (EOFException e){
            return new ResponseEntity<>("File upload fail", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{filename:.+}")
    public ResponseEntity<Object> deleteImage(@PathVariable("filename") String filename) {
        try {
            File deleteFile = new File(FILE_DIRECTORY + filename);
            deleteFile.delete();
            return new ResponseEntity<>("File Delete complete", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("File Delete fail", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/updatename/{id}/{productname}")
    public ResponseEntity<Object> updateNameImage(@PathVariable("productname") String productName,@PathVariable Integer id){
        String imageNameFromDB = productRepositories.findById(id).get().getImageName();
        String productNameImage = productRepositories.findById(id).get().getProdName();
        int countNameImageOriginal = 0;
        for (int i=0; i < productNameImage.length(); i++ ){
            if(productNameImage.charAt(i) != ' ') {
                countNameImageOriginal++;
            }
        }
        String imageNameOriginal = imageNameFromDB.substring(countNameImageOriginal);
        System.out.println(imageNameOriginal);
          String newImageName =  productName +  imageNameOriginal;
        File oldImageName = new File(FILE_DIRECTORY + imageNameFromDB);
        oldImageName.renameTo(new File(FILE_DIRECTORY + newImageName));
          productRepositories.findById(id).map(products -> {
              products.setImageName(newImageName);
              products.setProdName(productName);
              return productRepositories.save(products);
          });
        return new ResponseEntity<>("File Image Name Change complete", HttpStatus.OK);
    }

    @PutMapping("/update/{productName}/{filename:.+}")
    public ResponseEntity<Object> updateImage(@RequestParam("File") MultipartFile file, @PathVariable("filename") String filename,@PathVariable("productName") String productName) throws IOException {
        try{
//        if(filename != "" && productName != ""){
            System.out.println("OldNamePic: "+filename);
            this.deleteImage(filename);
            this.imageUpload(file,productName);
            return new ResponseEntity<>("File update complete", HttpStatus.OK);
//        }else
//            return new ResponseEntity<>("File update Fail", HttpStatus.BAD_REQUEST);
        } catch (EOFException e){
            return new ResponseEntity<>("File upload fail", HttpStatus.BAD_REQUEST);
        }

    }
}
