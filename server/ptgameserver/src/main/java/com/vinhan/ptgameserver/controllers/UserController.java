/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import static com.vinhan.ptgameserver.config.ConfigInfo.MAPPER;
import com.vinhan.ptgameserver.constant.StatusCode;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.mapclass.User;
import com.vinhan.ptgameserver.rule.ValidateRule;
import com.vinhan.ptgameserver.services.UserService;
import static com.vinhan.ptgameserver.utils.ConverterUtils.request2Json;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnListUser;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnMap;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnUser;
import com.vinhan.ptgameserver.utils.ReponseUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    ValidateRule validateRule;

    private final DozerBeanMapper mapper = new DozerBeanMapper();

    @ApiOperation(value = "Login")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "request", value = "request", required = true, dataType = "SWRQlogin", paramType = "body"),})
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
    public String registerAccount(@RequestBody User user) {
        try {
            if (!validateRule.isUserValid(user)) {
                return ReponseUtils.Invalid();
            }
            UserModel addDb = mapper.map(user, UserModel.class);
            boolean isDuplicate = userService.isExistUserName(addDb.getUserName());
            if (isDuplicate) {
                return ReponseUtils.Duplicate();
            } else {
                userService.addUserModel(addDb);
                return ReponseUtils.succesDone();
            }
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @GetMapping(value = "/allaccount", produces = "application/json")
    public String getAllAcount() {
        try {
            List<User> rs = userService.getAllUser();
            return ReponseUtils.success(StatusCode.SUCCESS, returnListUser(rs));
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }
}
