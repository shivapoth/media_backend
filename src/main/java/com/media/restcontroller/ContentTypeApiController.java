package com.media.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.media.entity.ContentTypeApiEntity;
import com.media.entity.LanguageApiEntity;
import com.media.repo.ContentTypeApiRepo;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins = "http://localhost:3000")
public class ContentTypeApiController {

	@Autowired
	private ContentTypeApiRepo contentTypeRepo;

	@PostMapping("/add")
	public ResponseEntity<String> addLanguage(@RequestBody ContentTypeApiEntity contApiEnt) {

		ContentTypeApiEntity save = contentTypeRepo.save(contApiEnt);

		if (save != null) {
			return ResponseEntity.ok(" Content Type Added Successfully");
		}

		else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Content");
		}

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ContentTypeApiEntity> getLanguage(@PathVariable("id") Integer id) {

		Optional<ContentTypeApiEntity> content = contentTypeRepo.findById(id);

		if (content.isPresent()) {

			return ResponseEntity.ok(content.get());

		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateLanguage(@PathVariable("id") Integer id,
			@RequestBody ContentTypeApiEntity updatedContent) {

		Optional<ContentTypeApiEntity> existingContent = contentTypeRepo.findById(id);

		if (existingContent.isPresent()) {

			ContentTypeApiEntity content = existingContent.get();

			content.setContentType(updatedContent.getContentType());

			contentTypeRepo.save(content);	

			return ResponseEntity.ok("Content Type updated successfully");

		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLanguage(@PathVariable("id") Integer id) {

		Optional<ContentTypeApiEntity> language = contentTypeRepo.findById(id);

		if (language.isPresent()) {

			contentTypeRepo.delete(language.get());

			return ResponseEntity.ok("Content Type deleted successfully");

		} else {

			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ContentTypeApiEntity>> getAllLanguages() {

		List<ContentTypeApiEntity> findAll = contentTypeRepo.findAll();

		if (!findAll.isEmpty()) {

			return ResponseEntity.ok(findAll);
		}

		else {

			return ResponseEntity.notFound().build();
		}

	}

}
