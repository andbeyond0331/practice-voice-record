package com.practice.practicevoicerecord.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service {
  @Autowired
  private AmazonS3 s3Client;

  private final String BUCKET_NAME = "practice-voice-record";

  public String uploadFile(MultipartFile file) throws IOException {
    File convertedFile = convertMultiPartToFile(file);
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    s3Client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, convertedFile));
    convertedFile.delete();
    return "File uploaded successfully: " + fileName;
  }

  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }
}
