package com.booksapi.controller;

import com.booksapi.service.AuthorService;
import com.booksapi.service.FilesDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private FilesDBService filesDBService;
    @Value("${profile.image}")
    private String local_path;

    @GetMapping(value = "/ProfileImage/Profile/{source}/{folder_loc}/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void serverImage(@PathVariable String source, @PathVariable String folder_loc, @PathVariable String imageName, HttpServletResponse response) throws IOException {

        String fullPath = local_path + source + File.separator + folder_loc + File.separator + imageName;
        InputStream resource = this.filesDBService.getResource(fullPath);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());


    }
}
