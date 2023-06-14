package com.media.restcontroller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.media.entity.ContentEntity;
import com.media.entity.ContentTypeApiEntity;
import com.media.entity.LanguageApiEntity;
import com.media.repo.ContentRepo;

@RestController
@RequestMapping("/filestore")
public class FileStorageController {

	@Value("${app.rootstorage}")
	private String rootStorage;

	@Autowired
	private ContentRepo contentRepo;

	@Autowired
	private ContentEntity contentEntity;

	@Autowired
	private LanguageApiEntity languageEntity;

	@PostMapping("/database")
	public ResponseEntity submitForm(@RequestParam("title") String title,
			@RequestParam("contentType") String contentType, @RequestParam("language") String language,
			@RequestParam("browse") MultipartFile file, @RequestParam("content") String content) {

		String folderPath = rootStorage + contentType;

		File folder = new File(folderPath);

		folder.mkdirs();

		String fileName = file.getOriginalFilename();

		String fileFullPath = folderPath + "/" + fileName;

		try {

			FileOutputStream fileOutputStream = new FileOutputStream(fileFullPath);

			fileOutputStream.write(file.getBytes());

			fileOutputStream.close();

			System.out.println("File saved successfully: " + fileFullPath);

		} catch (IOException e) {

			System.out.println("Error saving the file: " + e.getMessage());

			e.printStackTrace();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmssSSS");

		String currentDateAndTime = dateFormat.format(new Date());

		contentEntity.setTitle(title);

		contentEntity.setContentPath(currentDateAndTime);

		contentEntity.setEndPoint(fileFullPath);

		contentEntity.setPublishedDate(new Date());

		contentEntity.setLastModifiedDate(new Date());

		contentEntity.setPublishedBy("Content Creator");

		contentEntity.setStatus(1);

		contentRepo.save(contentEntity);

		return ResponseEntity.ok(contentEntity);

	}
}
