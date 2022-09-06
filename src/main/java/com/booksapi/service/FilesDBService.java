package com.booksapi.service;

import com.booksapi.model.entities.FilesSystemData;
import com.booksapi.model.entities.FilesModel;
import com.booksapi.payload.APIResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public interface FilesDBService {

    FilesModel getFileById(String id);
    public APIResponse fileSave(MultipartFile file) throws IOException;
    Stream<FilesModel> getAllFiles();

    FilesSystemData getFileByIdFromFileSystem(String  filePath);
    FilesSystemData uploadFileToFileSystem(MultipartFile file, String path) throws IOException;


    public InputStream getResource(String path) throws FileNotFoundException;




}
