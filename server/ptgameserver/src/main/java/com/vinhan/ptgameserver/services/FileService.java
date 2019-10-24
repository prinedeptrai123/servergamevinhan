/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.services;

import static com.vinhan.ptgameserver.config.ConfigInfo.UPLOAD_DIR;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Qui
 */
//@Service
//public class FileService {
//
//    private final Path fileStorageLocation;
//
//    @Autowired
//    public FileService() throws IOException {
//        this.fileStorageLocation = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
//        Files.createDirectories(this.fileStorageLocation);
//    }
//
//    public String storeFile(MultipartFile file) throws IOException {
//        String fileName = UUID.randomUUID().toString() + StringUtils.cleanPath(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));;
////        String fileName = UUID.randomUUID().toString();
//        Path targetLocation = this.fileStorageLocation.resolve(fileName);
//        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//        return fileName;
//    }
//}
