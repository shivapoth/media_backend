package com.media.restcontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.media.entity.ContentEntity;
import com.media.model.MediaModel;
import com.media.repo.ContentRepo;

@RestController
@RequestMapping("/filestore")
@CrossOrigin(origins = "http://localhost:3000")
public class FileStorageController {

    @Value("${app.rootstorage}")
    private String rootStorage;

    @Autowired
    private ContentRepo contentRepo;

    @PostMapping("/database")
    public ResponseEntity<?> submitForm(@RequestPart("model") MediaModel model, @RequestPart("file") List<MultipartFile> files) {
        String folderPath = rootStorage + "media/" + model.getContentType();
        File folder = new File(folderPath);
        folder.mkdirs();

        List<String> savedFilePaths = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileFullPath = folderPath + "/" + file.getOriginalFilename();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileFullPath);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
                savedFilePaths.add(fileFullPath);
                System.out.println("File saved successfully: " + fileFullPath);
            } catch (IOException e) {
                System.out.println("Error saving the file: " + e.getMessage());
                e.printStackTrace();
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmssSSS");
        String currentDateAndTime = dateFormat.format(new Date());

        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setTitle(model.getTitle());
        contentEntity.setContentType(model.getContentType());
        contentEntity.setLanguage(model.getLanguage());
        contentEntity.setEndPoint(String.join(",", savedFilePaths));
        contentEntity.setContent(model.getContent());
        contentEntity.setContentPath(currentDateAndTime);
        contentEntity.setPublishedDate(new Date());
        contentEntity.setLastModifiedDate(new Date());
        contentEntity.setPublishedBy("Content Creator");
        contentEntity.setStatus(1);

        contentRepo.save(contentEntity);

        return ResponseEntity.ok(contentEntity);
    }
}
