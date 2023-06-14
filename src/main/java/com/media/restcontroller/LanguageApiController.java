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

import com.media.entity.LanguageApiEntity;
import com.media.repo.LanguageApiRepo;

@RestController
@RequestMapping("/language")
@CrossOrigin(origins = "http://localhost:3000")
public class LanguageApiController {

	@Autowired
	private LanguageApiRepo langRepo;

	@PostMapping("/add")
	public ResponseEntity<String> addLanguage(@RequestBody LanguageApiEntity langApiEnt) {

		LanguageApiEntity save = langRepo.save(langApiEnt);

		if (save != null) {
			return ResponseEntity.ok("Added Successfully");
		}

		else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add language");
		}

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<LanguageApiEntity> getLanguage(@PathVariable("id") Integer id) {

		Optional<LanguageApiEntity> language = langRepo.findById(id);

		if (language.isPresent()) {

			return ResponseEntity.ok(language.get());

		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateLanguage(@PathVariable("id") Integer id,
			@RequestBody LanguageApiEntity updatedLanguage) {

		Optional<LanguageApiEntity> existingLanguage = langRepo.findById(id);

		if (existingLanguage.isPresent()) {

			LanguageApiEntity language = existingLanguage.get();

			language.setLanguage(updatedLanguage.getLanguage());

			langRepo.save(language);

			return ResponseEntity.ok("Language updated successfully");

		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteLanguage(@PathVariable("id") Integer id) {

		Optional<LanguageApiEntity> language = langRepo.findById(id);

		if (language.isPresent()) {

			langRepo.delete(language.get());

			return ResponseEntity.ok("Language deleted successfully");

		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<LanguageApiEntity>> getAllLanguages() {

		List<LanguageApiEntity> findAll = langRepo.findAll();

		if (!findAll.isEmpty()) {

			return ResponseEntity.ok(findAll);
		}

		else {

			return ResponseEntity.notFound().build();
		}

	}

}
