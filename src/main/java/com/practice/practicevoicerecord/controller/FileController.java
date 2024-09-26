package com.practice.practicevoicerecord.controller;

import com.practice.practicevoicerecord.service.S3Service;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

  @Autowired
  private S3Service s3Service;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
    try {
      String result = s3Service.uploadFile(file);
      return ResponseEntity.ok(result);
    } catch (IOException e) {
      return ResponseEntity.status(500).body("File upload failed.");
    }
  }
}
