package com.booksapi.util;

import com.booksapi.model.entities.FilesSystemData;
import com.booksapi.service.FilesDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Component
public class FileUtil {

    @Autowired
    private FilesDBService filesDBService;

    public String uploadFileToFileSystem(MultipartFile file, String path) {
        String fileDownloadUri = "";
        try {
            FilesSystemData filesSystemData = filesDBService.uploadFileToFileSystem(file, path);

            fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/" + path + "/")
                    .path(String.valueOf(filesSystemData.getFilePath()))
                    .toUriString();
        } catch (IOException e) {
            new RuntimeException(e);
        }
        return fileDownloadUri;
    }
}
