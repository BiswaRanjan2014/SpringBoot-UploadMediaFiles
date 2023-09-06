package com.dakr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dakr.entity.MediaFile;

public interface MediaFileRepository extends JpaRepository<MediaFile, Long>{

}
