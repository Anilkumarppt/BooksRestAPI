package com.booksapi.controller;

import com.booksapi.model.dto.AuthorDto;
import com.booksapi.model.dto.UserDto;
import com.booksapi.model.entities.FilesModel;
import com.booksapi.model.entities.FilesSystemData;
import com.booksapi.payload.APIResponse;
import com.booksapi.payload.FileResponse;
import com.booksapi.service.AuthorService;
import com.booksapi.service.FilesDBService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private FilesDBService filesDBService;

    @Autowired
    private ModelMapper mapper;
    @Value("${profile.image}")
    private  String local_path;

    @PostMapping("/author")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorDto author = authorService.createAuthor(authorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/author/{author_id}")
    public ResponseEntity<?> getAuthor(@PathVariable("author_id") int id) {
        AuthorDto authorDto = authorService.getAuthor(id);
        return ResponseEntity.ok(authorDto);
    }

    @PutMapping("/author/update/{author_id}")
    public ResponseEntity<?> updateAuthor(@RequestBody AuthorDto authorDto, int author_id) {
        AuthorDto updatedAuthor = authorService.updateAuthor(authorDto, author_id);
        return ResponseEntity.ok(updatedAuthor);
    }

    @GetMapping("author/find-all")
    public ResponseEntity<?> getAllAuthors() {
        List<AuthorDto> allAuthors = authorService.getAllAuthors();
        return ResponseEntity.ok(allAuthors);
    }

    @DeleteMapping("author/delete/{author_id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("author_id") int id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Delete Author Successfully!", HttpStatus.OK);
    }


    @PostMapping("/author/profile")
    public ResponseEntity<APIResponse> uploadAuthorProfile(@RequestParam("profile") MultipartFile file) {
        APIResponse apiResponse = new APIResponse();
        try {

            try {
                apiResponse = filesDBService.fileSave(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
        } catch (InvalidDataAccessResourceUsageException e) {
            System.out.println(e.getCause().getCause().getMessage());
            apiResponse.setMessage(e.getCause().getMessage());
            apiResponse.setStatus(HttpStatus.CONFLICT);
            apiResponse.setSuccess(false);
            return ResponseEntity.ok(apiResponse);
        }
    }
    /*Store profile picture Data in the form of Binary data into the mysql Database */
    /*@GetMapping("/author/profile/{Id}")
    public ResponseEntity<?> getFile(@PathVariable("Id") String id) {
        FilesModel filesModel = filesDBService.getFileById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(filesModel.getType()))
                .body(filesModel.getData());
        *//*FileResponse filesModel=filesStorageService.getFileById(id);
        return  ResponseEntity.ok(filesModel);*//*
    }

    @GetMapping("author/files")
    public ResponseEntity<List<FileResponse>> getListFiles() {
        List<FileResponse> files = filesDBService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();
            return new FileResponse(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }*/

    @PostMapping("author/{author_id}/profile")
    public ResponseEntity<?> uploadFileToFileSystem(@PathVariable(name = "author_id") int authorId,
                                                    @RequestParam("profile") MultipartFile file)
            throws IOException {

        AuthorDto author = this.authorService.getAuthor(authorId);
        FilesSystemData filesSystemData = filesDBService.uploadFileToFileSystem(file, local_path+"/author");

        System.out.println(filesSystemData.getFilePath());

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/"+local_path+"/")
                .path(String.valueOf(filesSystemData.getFilePath()))
                .toUriString();

        System.out.println("Author Image "+fileDownloadUri);
        author.setImage(fileDownloadUri);

        authorService.updateAuthor(author, authorId);

        APIResponse apiResponse = new APIResponse("Uploaded Successfully", HttpStatus.OK, true);
        return ResponseEntity.ok(apiResponse);

    }


}
