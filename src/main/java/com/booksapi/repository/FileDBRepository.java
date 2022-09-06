package com.booksapi.repository;

import com.booksapi.model.entities.FilesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FilesModel,String> {
}
