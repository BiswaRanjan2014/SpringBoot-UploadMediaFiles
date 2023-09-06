package com.dakr.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dakr.entity.MediaFile;

public interface MediaFileService {

	MediaFile uploadFile(MultipartFile file) throws IOException;
    MediaFile getFileById(Long id);
    List<MediaFile> getAllFiles();
    MediaFile updateFile(Long id, MultipartFile file) throws IOException;
    void deleteFile(Long id);
	
}
