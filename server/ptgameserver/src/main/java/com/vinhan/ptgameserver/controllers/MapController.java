/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.vinhan.ptgameserver.config.ConfigInfo;
import static com.vinhan.ptgameserver.config.ConfigInfo.DEFAULT_MAP;
import static com.vinhan.ptgameserver.config.ConfigInfo.UPLOAD_FOLDER;
import static com.vinhan.ptgameserver.config.ConfigInfo.URL_FORMAT;
import com.vinhan.ptgameserver.constant.StatusCode;
import com.vinhan.ptgameserver.db.StoreRepository;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.services.UserService;
import com.vinhan.ptgameserver.storage.StorageService;
import static com.vinhan.ptgameserver.utils.ConverterUtils.request2Json;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnMap;
import com.vinhan.ptgameserver.utils.ReponseUtils;
import java.io.IOException;
import java.nio.file.Path;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Qui
 */
@RestController
@RequestMapping("/api/map")
public class MapController {

    private final StorageService storageService;

    @Autowired
    UserService userService;

    @Autowired
    public MapController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/{userId}", produces = "application/json")
    public String getMapOfUser(@PathVariable(name = "userId") int userId) {
        try {

            return ReponseUtils.succesDone();
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @PostMapping(value = "/savefile-{userId}", produces = "application/json")
    public String saveFileMap(@PathVariable(name = "userId") int userId, @RequestParam("file") MultipartFile file) {

        try {
            boolean isExist = userService.isExistId(userId);
            if (!isExist) {
                return ReponseUtils.NotFoundString();
            }
            //store file
            if (!file.getOriginalFilename().isEmpty()) {
                String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
                String fileName = String.format(URL_FORMAT, userId, fileExtension);
                storageService.storeWithFileName(file, fileName);

                //update map file
                userService.updateUrlMap(userId, UPLOAD_FOLDER + fileName);
            } else {
                //set default map
                userService.updateUrlMap(userId, UPLOAD_FOLDER + DEFAULT_MAP);
            }
            return ReponseUtils.succesDone();

        } catch (Exception e) {
            System.err.println(e);
        }
        return ReponseUtils.ServerError();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name = "userId") int userId, HttpServletRequest request) {
        boolean isExist = userService.isExistId(userId);
        String fileName = DEFAULT_MAP;

        if (isExist) {
            fileName = String.format(URL_FORMAT, userId, "DAT");
        } else {
            //do nothing
        }
        //get fileName
        // Load file as Resource
        Resource resource = storageService.loadAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
}
