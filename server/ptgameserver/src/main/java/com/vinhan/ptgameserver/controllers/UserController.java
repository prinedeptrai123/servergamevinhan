/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.vinhan.ptgameserver.constant.StatusCode;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.services.UserService;
import static com.vinhan.ptgameserver.utils.ConverterUtils.request2Json;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnMap;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnUser;
import com.vinhan.ptgameserver.utils.ReponseUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Qui
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login", produces = "application/json")
    public String login(HttpServletRequest request) {
        try {
            JsonNode body = request2Json(request);
            if (body.has("username") && body.has("password")) {
                String username = body.get("username").asText();
                String password = body.get("password").asText();

                UserModel result = userService.login(username, password);
                if (result != null) {
                    return ReponseUtils.success(StatusCode.SUCCESS, returnUser(result));
                } else {
                    return ReponseUtils.NotFound();
                }
            }

        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @PostMapping(value = "/register", produces = "application/json")
    public String registerAccount() {
        try {
            UserModel addDb = new UserModel();
            addDb.setUserName("letuongqui");
            addDb.setPassWord("123456789");
            addDb.setUrlMap("null");
            //check if duplicate
            boolean isDuplicate = userService.isExistUserName(addDb.getUserName());
            if (!isDuplicate) {
                userService.addUserModel(addDb);
            }
            return "succes";

        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }
}
