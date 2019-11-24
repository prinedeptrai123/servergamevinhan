/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.vinhan.ptgameserver.constant.StatusCode;
import com.vinhan.ptgameserver.db.StoreRepository;
import com.vinhan.ptgameserver.entities.TrooperModel;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.mapclass.Trooper;
import com.vinhan.ptgameserver.mapclass.UserTrooper;
import com.vinhan.ptgameserver.services.TrooperService;
import static com.vinhan.ptgameserver.utils.ConverterUtils.request2Json;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnListTrooper;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnListUser;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnListUserTrooper;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnUser;
import com.vinhan.ptgameserver.utils.ReponseUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Qui
 */
@RestController
@RequestMapping("/api/trooper")
public class TrooperController {

    @Autowired
    TrooperService trooperSerive;

    @ApiOperation(value = "Lấy danh sách tất cả các loại lính")
    @GetMapping(value = "/get-all", produces = "application/json")
    public String getAllTrooper() {
        try {
            List<Trooper> result = trooperSerive.getAllTrooper();
            return ReponseUtils.success(StatusCode.SUCCESS, returnListTrooper(result));
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @ApiOperation(value = "Lấy danh sách lính mà user đang sở hữu")
    @GetMapping(value = "/user-{userID}/get-all", produces = "application/json")
    public String getAllTrooperOfUser(@PathVariable(name = "userID") int userID) {
        try {
            List<UserTrooper> result = trooperSerive.getTrooperByUserID(userID);
            return ReponseUtils.success(StatusCode.SUCCESS, returnListUserTrooper(result));
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @ApiOperation(value = "Trừ đi số lượng lính của user", notes = ""
            + "Body truyền vào như sau:\n"
            + "{\"1\":1,\"2\":1,\"3\":1}\n"
            + "Với \"1\",\"2\",\"3\" là id của lính cần trừ")
    @PostMapping(value = "/user-{userID}/decrease-trooper", produces = "application/json")
    public String deacreaseTrooper(@PathVariable(name = "userID") int userID,
            @RequestBody Map<Integer, Integer> request) {
        try {
            System.out.println(userID);
            request.forEach((trooperID, count) -> {
                System.out.println(trooperID);
                System.out.println(count);

                trooperSerive.deacreaseTrooperByUserID(userID, trooperID, count);
            });
            return ReponseUtils.succesDone();
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @ApiOperation(value = "Cộng thêm số lượng lính cho user", notes = ""
            + "Body truyền vào như sau:\n"
            + "{\"1\":1,\"2\":1,\"3\":1}\n"
            + "Với \"1\",\"2\",\"3\" là id của lính cần cộng")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "request", value = "request", required = true, dataType = "SWRQaddExperience", paramType = "body"),})
    @PostMapping(value = "/user-{userID}/increase-trooper", produces = "application/json")
    public String increaseTrooper(@PathVariable(name = "userID") int userID, @RequestBody Map<Integer, Integer> request) {
        try {
            request.forEach((trooperID, count) -> {
                trooperSerive.increaseTrooperByUserID(userID, trooperID, count);
            });
            return ReponseUtils.succesDone();
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }
}
