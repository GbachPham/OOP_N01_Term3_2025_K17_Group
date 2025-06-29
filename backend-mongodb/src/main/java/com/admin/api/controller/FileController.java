package com.admin.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admin.api.entity.Image;
import com.admin.api.repository.ImageRepository;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
            }

            // Kiểm tra loại file ảnh
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body(Map.of("error", "File must be an image"));
            }

            // Tạo tên file unique
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            // Tạo và lưu Image entity vào MongoDB
            Image image = new Image(
                uniqueFilename,
                originalFilename,
                contentType,
                file.getSize(),
                file.getBytes()
            );
            
            Image savedImage = imageRepository.save(image);

            // Trả về thông tin file
            Map<String, String> response = new HashMap<>();
            response.put("fileId", savedImage.getId());
            response.put("fileName", originalFilename);
            response.put("url", "/files/" + savedImage.getId());
            response.put("size", String.valueOf(file.getSize()));

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Could not store file: " + e.getMessage()));
        }
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String imageId) {
        try {
            Optional<Image> imageOpt = imageRepository.findById(imageId);
            
            if (imageOpt.isPresent()) {
                Image image = imageOpt.get();
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(image.getContentType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getOriginalFileName() + "\"")
                        .body(image.getData());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Map<String, String>> deleteFile(@PathVariable String imageId) {
        try {
            Optional<Image> imageOpt = imageRepository.findById(imageId);
            
            if (imageOpt.isPresent()) {
                imageRepository.deleteById(imageId);
                return ResponseEntity.ok(Map.of("message", "File deleted successfully"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Could not delete file: " + e.getMessage()));
        }
    }

    @GetMapping("/{imageId}/info")
    public ResponseEntity<Map<String, Object>> getFileInfo(@PathVariable String imageId) {
        try {
            Optional<Image> imageOpt = imageRepository.findById(imageId);
            
            if (imageOpt.isPresent()) {
                Image image = imageOpt.get();
                
                Map<String, Object> fileInfo = new HashMap<>();
                fileInfo.put("filename", image.getFileName());
                fileInfo.put("originalFilename", image.getOriginalFileName());
                fileInfo.put("size", image.getSize());
                fileInfo.put("contentType", image.getContentType());
                fileInfo.put("createdAt", image.getCreatedAt().toString());
                fileInfo.put("url", "/files/" + image.getId());
                
                return ResponseEntity.ok(fileInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 