/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.vinhan.ptgameserver.constant.StatusCode;
import com.vinhan.ptgameserver.mapclass.Building;
import com.vinhan.ptgameserver.mapclass.Trooper;
import com.vinhan.ptgameserver.mapclass.UserBuilding;
import com.vinhan.ptgameserver.services.BuildingService;
import com.vinhan.ptgameserver.services.TrooperService;
import static com.vinhan.ptgameserver.utils.ConverterUtils.request2Json;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnListBuilding;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnListTrooper;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnListUserBuilding;
import com.vinhan.ptgameserver.utils.ReponseUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/building")
public class BuildingController {

    @Autowired
    BuildingService buildingService;

    @ApiOperation(value = "Lấy danh sách tất cả các loại nhà")
    @GetMapping(value = "/get-all", produces = "application/json")
    public String getAllBuilding() {
        try {
            List<Building> result = buildingService.getAllBuilding();
            return ReponseUtils.success(StatusCode.SUCCESS, returnListBuilding(result));
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @ApiOperation(value = "Lấy danh sách tất cả các loại nhà của user")
    @GetMapping(value = "/user-{userID}/get-all", produces = "application/json")
    public String getAllBuildingOfUser(@PathVariable(name = "userID") int userID) {
        try {
            List<UserBuilding> result = buildingService.getBuildingByUserID(userID);
            return ReponseUtils.success(StatusCode.SUCCESS, returnListUserBuilding(result));
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @ApiOperation(value = "Trừ đi số lượng nhà khi nhà bị đập")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "request", value = "request", required = true, dataType = "SWRQdeacreaseBuilding", paramType = "body"),})
    @PostMapping(value = "/user-{userID}/decrease-building", produces = "application/json")
    public String deacreaseBuilding(@PathVariable(name = "userID") int userID, HttpServletRequest request) {
        try {
            JsonNode body = request2Json(request);
            if (body.has("buildingID") && body.has("count")) {
                int buildingID = body.get("buildingID").asInt();
                int count = body.get("count").asInt();
                boolean result = buildingService.deacreaseBuildingByUserID(userID, buildingID, count);

                return result == true ? ReponseUtils.succesDone() : ReponseUtils.NotFound();
            }
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }
    
    @ApiOperation(value = "Cộng thêm số lượng nhà khi xây mới")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "request", value = "request", required = true, dataType = "SWRQdeacreaseBuilding", paramType = "body"),})
    @PostMapping(value = "/user-{userID}/increase-building", produces = "application/json")
    public String increaseBuilding(@PathVariable(name = "userID") int userID, HttpServletRequest request) {
        try {
            JsonNode body = request2Json(request);
            if (body.has("buildingID") && body.has("count")) {
                int buildingID = body.get("buildingID").asInt();
                int count = body.get("count").asInt();
                boolean result = buildingService.increaseBuildingByUserID(userID, buildingID, count);

                return result == true ? ReponseUtils.succesDone() : ReponseUtils.NotFound();
            }
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }
}
