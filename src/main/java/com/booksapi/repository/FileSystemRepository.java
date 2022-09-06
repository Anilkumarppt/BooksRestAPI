package com.booksapi.repository;

import com.booksapi.model.entities.FilesSystemData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileSystemRepository extends JpaRepository<FilesSystemData, Long> {
}
