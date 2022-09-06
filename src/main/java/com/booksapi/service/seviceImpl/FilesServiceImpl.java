package com.booksapi.service.seviceImpl;

import com.booksapi.exception.ResourceNotFoundEx;
import com.booksapi.model.entities.FilesSystemData;
import com.booksapi.model.entities.FilesModel;
import com.booksapi.payload.APIResponse;
import com.booksapi.repository.FileDBRepository;
import com.booksapi.repository.FileSystemRepository;
import com.booksapi.service.FilesDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FilesServiceImpl implements FilesDBService {

    @Autowired
    FileDBRepository fileDBRepository;

    @Value("${profile.image}")
    private  String local_path;


    @Autowired
    private FileSystemRepository fileSystemRepository;
    @Override
    public FilesModel getFileById(String  id) {
        try {
            Optional<FilesModel> response = fileDBRepository.findById(id);
            if (response.isPresent()) {
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/files/")
                        .path(String.valueOf(response.get().getId()))
                        .toUriString();
                System.out.println(fileDownloadUri);
                return response.get();
            } else
                return new FilesModel();
        }
        catch (InvalidDataAccessResourceUsageException exception){
            System.out.println(exception.getCause().toString());
            return new FilesModel();
        }
    }

    @Override
    public APIResponse fileSave(MultipartFile file) throws IOException {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        FilesModel filesModel=new FilesModel(fileName,file.getContentType(),file.getBytes());
        FilesModel newFiles=fileDBRepository.save(filesModel);
        if(newFiles!=null){
            System.out.println("New Files Created Successfully");
            return new APIResponse("Files Uploaded Successfully", HttpStatus.OK,true);
        }
        return null;
    }

    @Override
    public Stream<FilesModel> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public FilesSystemData getFileByIdFromFileSystem(String  filePath) {
        Optional<FilesSystemData> fileData = fileSystemRepository.findById(2L);
        return fileData.orElse(null);
        /*Optional<FilesSystemData> filesSystemData = fileSystemRepository.find(id);
        return filesSystemData.orElse(null);*/
    }

    @Override
    public FilesSystemData uploadFileToFileSystem(MultipartFile file, String path) throws IOException {
        //path=ProfileImage/Profile/author/1
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String newFileNameWithExtension = UUID.randomUUID().toString() + extension;
        String originalPath = path + File.separator+newFileNameWithExtension;
        //ProfileImage/Profile/author+newFileNameWithExtension
        File file1 = new File(path);
        if (!file1.exists()) {
            boolean mkdirs = file1.mkdirs();
            System.out.println(mkdirs);
        }
        Files.copy(file.getInputStream(), Paths.get(originalPath));
        FilesSystemData filesSystemData=new FilesSystemData(file.getOriginalFilename(),file.getContentType(),file.getSize(),newFileNameWithExtension);
        FilesSystemData savedFile = fileSystemRepository.save(filesSystemData);
        //http://localhost:8087/ProfileImage/Profile/08c10e4d-7658-4f43-a687-b8b042463085.jpeg
        return savedFile;
    }

    @Override
    public InputStream getResource(String path) throws FileNotFoundException {


        FileInputStream fileInputStream = new FileInputStream(path);


        return fileInputStream;
    }
}
