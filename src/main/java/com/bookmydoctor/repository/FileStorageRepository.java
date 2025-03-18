package com.bookmydoctor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmydoctor.entity.FileStorage;

public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
	 Optional<FileStorage> findByFileName(String fileName);
}
