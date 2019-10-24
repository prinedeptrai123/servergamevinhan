/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.vinhan.ptgameserver.constant.StatusCode;
import com.vinhan.ptgameserver.db.StoreRepository;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.services.UserService;
import static com.vinhan.ptgameserver.utils.ConverterUtils.request2Json;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnMap;
import com.vinhan.ptgameserver.utils.ReponseUtils;
import javax.servlet.http.HttpServletRequest;
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

//    @Autowired
//    FileService fileService;
    @GetMapping(value = "/{userId}", produces = "application/json")
    public String getMapOfUser(@PathVariable(name = "id") int id) {
        try {

            return ReponseUtils.succesDone();
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @PostMapping(value = "/savefile", produces = "application/json")
    public String saveFileMap(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

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
