package com.dakr.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dakr.entity.MediaFile;
import com.dakr.repository.MediaFileRepository;

@Service
public class MediaFileServiceImpl implements MediaFileService {

	@Autowired
	private MediaFileRepository mediaFileRepository;

	@Override
	public MediaFile uploadFile(MultipartFile file) throws IOException {
		MediaFile mediaFile = new MediaFile();
		mediaFile.setName(file.getOriginalFilename());
		mediaFile.setData(file.getBytes());
		return mediaFileRepository.save(mediaFile);
	}

	@Override
	public MediaFile getFileById(Long id) {
		return mediaFileRepository.findById(id).orElse(null);
	}

	@Override
	public List<MediaFile> getAllFiles() {
		return mediaFileRepository.findAll();
	}

	@Override
	public MediaFile updateFile(Long id, MultipartFile file) throws IOException {
		MediaFile mediaFile = mediaFileRepository.findById(id).orElse(null);
		if (mediaFile != null) {
			mediaFile.setName(file.getOriginalFilename());
			mediaFile.setData(file.getBytes());
			return mediaFileRepository.save(mediaFile);
		}
		return null;
	}

	@Override
	public void deleteFile(Long id) {
		mediaFileRepository.deleteById(id);
	}
}
