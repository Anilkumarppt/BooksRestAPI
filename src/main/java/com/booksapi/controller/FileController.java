package com.booksapi.controller;

import com.booksapi.model.dto.AuthorDto;
import com.booksapi.model.entities.FilesSystemData;
import com.booksapi.payload.APIResponse;
import com.booksapi.service.AuthorService;
import com.booksapi.service.FilesDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private FilesDBService filesDBService;
    @Value("${profile.image}")
    private  String local_path;





 /*   @GetMapping("ProfileImage/Profile/{author_id}")
    public ResponseEntity<?> getAuthorImage(@PathVariable(name = "author_id") int authorId){
        AuthorDto author = this.authorService.getAuthor(authorId);
        FilesSystemData fileByIdFromFileSystem = filesDBService.getFileByIdFromFileSystem(author.getImage());

        return ResponseEntity.ok("Ok");
    }*/

    @GetMapping(value = "/ProfileImage/Profile/{source}/{folder_loc}/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void serverImage(@PathVariable String source,@PathVariable String  folder_loc,@PathVariable String imageName, HttpServletResponse response) throws IOException {

        String fullPath = local_path+source+File.separator+folder_loc+File.separator+imageName;
        InputStream resource = this.filesDBService.getResource(fullPath);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource, response.getOutputStream());


    }
}
