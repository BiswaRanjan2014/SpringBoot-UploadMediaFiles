package com.dakr.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dakr.entity.MediaFile;
import com.dakr.service.MediaFileService;

@RestController
@RequestMapping("/media")
public class MediaFileController {

	@Autowired
	private MediaFileService mediaFileService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		String path_Directory;
		try {
			// Directory to save the file in ur project
			 path_Directory =
					 "D:\\AdvJava\\App\\DAKRPROJECT\\UploadMediaFile2\\src\\main\\resources\\static\\mediafiles";
			 
			//path_Directory = new ClassPathResource("static/mediafiles/").getFile().getAbsolutePath();

			// This will copy the source file(C:/download) image to Target
			// file(/static/image)
			Files.copy(file.getInputStream(), Paths.get(path_Directory + File.separator + file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			
			mediaFileService.uploadFile(file);
			
			return ResponseEntity.ok("File uploaded successfully.");
			
		} catch (IOException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getFileById(@PathVariable Long id) {
		MediaFile mediaFile = mediaFileService.getFileById(id);
		if (mediaFile != null) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + mediaFile.getName() + "\"")
					.body(mediaFile.getData());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/allmedia")
	public ResponseEntity<List<MediaFile>> getAllFiles() {
		List<MediaFile> files = mediaFileService.getAllFiles();
		return ResponseEntity.ok(files);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
		try {
			MediaFile updatedFile = mediaFileService.updateFile(id, file);
			if (updatedFile != null) {
				return ResponseEntity.ok("File updated successfully.");
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating file.");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFile(@PathVariable Long id) {
		mediaFileService.deleteFile(id);
		return ResponseEntity.ok("File deleted successfully.");
	}
}
