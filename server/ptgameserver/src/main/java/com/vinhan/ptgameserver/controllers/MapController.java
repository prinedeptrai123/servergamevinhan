/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.vinhan.ptgameserver.config.ConfigInfo;
import static com.vinhan.ptgameserver.config.ConfigInfo.URL_FORMAT;
import com.vinhan.ptgameserver.constant.StatusCode;
import com.vinhan.ptgameserver.db.StoreRepository;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.services.UserService;
import com.vinhan.ptgameserver.storage.StorageService;
import static com.vinhan.ptgameserver.utils.ConverterUtils.request2Json;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnMap;
import com.vinhan.ptgameserver.utils.ReponseUtils;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final String UPLOAD_URL = ConfigInfo.UPLOAD_URL;
    private final String UPLOAD_FOLDER = ConfigInfo.UPLOAD_FOLDER;

    private final StorageService storageService;

    @Autowired
    UserService userService;

    @Autowired
    public MapController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/{userId}", produces = "application/json")
    public String getMapOfUser(@PathVariable(name = "id") int id) {
        try {

            return ReponseUtils.succesDone();
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @PostMapping(value = "/savefile", produces = "application/json")
    public String saveFileMap( @RequestParam("file") MultipartFile file) {

//        try {
//            JsonNode body = request2Json(request);
//            if (!body.has("userId")) {
//                //check user
//                boolean isExist = userService.isExistId(body.get("userId").asInt());
//                if (!isExist) {
//                    return ReponseUtils.NotFound();
//                }
//                //store file
//                if (!file.getOriginalFilename().isEmpty()) {
//                    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
//                    String fileName = String.format(URL_FORMAT, 1, fileExtension);
//                    storageService.storeWithFileName(file, fileName);
//                }
//                return ReponseUtils.succesDone();
//
//            } else {
//                return ReponseUtils.Failure();
//            }
//
//        } catch (Exception e) {
//            System.err.println(e);
//        }
        
        //store file
                if (!file.getOriginalFilename().isEmpty()) {
                    String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
                    String fileName = String.format(URL_FORMAT, 1, fileExtension);
                    storageService.storeWithFileName(file, fileName);
                }
                return ReponseUtils.succesDone();
        

        //return ReponseUtils.ServerError();
    }

    @PostMapping(value = "/updateFile", produces = "application/json")
    public String updateFileMap(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        try {
            JsonNode body = request2Json(request);
            System.out.println(request.toString());

            if (body.has("userId")) {
                //String url = fileService.storeFile(file);

                return ReponseUtils.succesDone();

            } else {
                return ReponseUtils.Failure();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return ReponseUtils.ServerError();
    }
}
