package com.booksapi.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "file_data")
@Data

@NoArgsConstructor

public class FilesSystemData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Long fileSize;
    private String filePath;

    public FilesSystemData(String name, String type, Long fileSize, String filePath) {
        this.name = name;
        this.type = type;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }
}
