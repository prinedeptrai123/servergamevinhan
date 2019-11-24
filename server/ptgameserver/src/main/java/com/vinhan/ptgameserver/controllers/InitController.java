/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import static com.vinhan.ptgameserver.config.ConfigInfo.LINH_DANH_GAN;
import static com.vinhan.ptgameserver.config.ConfigInfo.LINH_DANH_XA;
import static com.vinhan.ptgameserver.config.ConfigInfo.LINH_XAY_NHA;
import com.vinhan.ptgameserver.constant.StatusCode;
import com.vinhan.ptgameserver.entities.BuildingModel;
import com.vinhan.ptgameserver.entities.TrooperModel;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.mapclass.UserTrooper;
import com.vinhan.ptgameserver.services.BuildingService;
import com.vinhan.ptgameserver.services.TrooperService;
import com.vinhan.ptgameserver.services.UserService;
import static com.vinhan.ptgameserver.utils.ConverterUtils.returnListUserTrooper;
import com.vinhan.ptgameserver.utils.ReponseUtils;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Qui
 */
@RestController
@RequestMapping("/api/master")
public class InitController {

    boolean isFirstTime = true;

    @Autowired
    UserService userService;

    @Autowired
    BuildingService buildingService;

    @Autowired
    TrooperService trooperService;

    @ApiOperation(value = "Chạy hàm này lần đầu tên để reset data")
    @GetMapping(value = "/init", produces = "application/json")
    public String initData() {
        try {
            if (isFirstTime) {
                initAccount();
                initTrooper();
                initBuilding();
                initBuildingUser();
                initTrooperUser();
                isFirstTime = false;
                return ReponseUtils.succesDone();
            }
            return ReponseUtils.NotFound();
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    private void initTrooper() {

        TrooperModel trp0 = new TrooperModel();
        trp0.setName("builder");
        trp0.setDame(0);
        trp0.setHp(50);
        trp0.setPrice(10.0);
        trp0.setSpeed(5);
        trp0.setSpornTime(2);
        trp0.setType(LINH_XAY_NHA);
        trp0.setRangeAttack(0);
        trp0.setSpeedAttack(10);

        TrooperModel trp1 = new TrooperModel();
        trp1.setName("swordsman");
        trp1.setDame(10);
        trp1.setHp(50);
        trp1.setPrice(10.0);
        trp1.setSpeed(5);
        trp1.setSpornTime(2);
        trp1.setType(LINH_DANH_GAN);
        trp1.setRangeAttack(0);
        trp1.setSpeedAttack(10);

        TrooperModel trp2 = new TrooperModel();
        trp2.setName("archer");
        trp2.setDame(10);
        trp2.setHp(50);
        trp2.setPrice(10.0);
        trp2.setSpeed(5.0);
        trp2.setSpornTime(2.0);
        trp2.setType(LINH_DANH_XA);
        trp2.setRangeAttack(50.0);
        trp2.setSpeedAttack(20.0);

        TrooperModel trp3 = new TrooperModel();
        trp3.setName("no name");
        trp3.setDame(10);
        trp3.setHp(50);
        trp3.setPrice(10.0);
        trp3.setSpeed(5.0);
        trp3.setSpornTime(2.0);
        trp3.setType(LINH_DANH_XA);
        trp3.setRangeAttack(50.0);
        trp3.setSpeedAttack(20.0);

        TrooperModel trp4 = new TrooperModel();
        trp4.setName("no name 2");
        trp4.setDame(10);
        trp4.setHp(50);
        trp4.setPrice(10.0);
        trp4.setSpeed(5.0);
        trp4.setSpornTime(2.0);
        trp4.setType(LINH_DANH_XA);
        trp4.setRangeAttack(50.0);
        trp4.setSpeedAttack(20.0);

        trooperService.addTrooper(trp0);
        trooperService.addTrooper(trp1);
        trooperService.addTrooper(trp2);
        trooperService.addTrooper(trp3);
        trooperService.addTrooper(trp4);

    }

    private void initBuilding() {
        BuildingModel bd1 = new BuildingModel();
        bd1.setName("army camp");
        bd1.setDame(0);
        bd1.setHp(50);
        bd1.setPrice(10.0);
        bd1.setSpeed(0);
        bd1.setBuildTime(10);
        bd1.setType(0);
        bd1.setRangeAttack(0);
        bd1.setType(0);
        bd1.setCapacity(5);

        BuildingModel bd2 = new BuildingModel();
        bd2.setName("barrack");
        bd2.setDame(0);
        bd2.setHp(50);
        bd2.setPrice(10.0);
        bd2.setSpeed(0);
        bd2.setBuildTime(10);
        bd2.setType(0);
        bd2.setRangeAttack(0);
        bd2.setType(0);
        bd2.setCapacity(5);

        BuildingModel bd3 = new BuildingModel();
        bd3.setName("builder Hut");
        bd3.setDame(0);
        bd3.setHp(50);
        bd3.setPrice(10.0);
        bd3.setSpeed(0);
        bd3.setBuildTime(10);
        bd3.setType(0);
        bd3.setRangeAttack(0);
        bd3.setType(0);
        bd3.setCapacity(5);

        BuildingModel bd4 = new BuildingModel();
        bd4.setName("cannon");
        bd4.setDame(0);
        bd4.setHp(50);
        bd4.setPrice(10.0);
        bd4.setSpeed(0);
        bd4.setBuildTime(10);
        bd4.setType(0);
        bd4.setRangeAttack(0);
        bd4.setType(0);
        bd4.setCapacity(5);

        BuildingModel bd5 = new BuildingModel();
        bd5.setName("Clan Castle");
        bd5.setDame(0);
        bd5.setHp(50);
        bd5.setPrice(10.0);
        bd5.setSpeed(0);
        bd5.setBuildTime(10);
        bd5.setType(0);
        bd5.setRangeAttack(0);
        bd5.setType(0);
        bd5.setCapacity(5);

        BuildingModel bd6 = new BuildingModel();
        bd6.setName("Defense");
        bd6.setDame(0);
        bd6.setHp(50);
        bd6.setPrice(10.0);
        bd6.setSpeed(0);
        bd6.setBuildTime(10);
        bd6.setType(0);
        bd6.setRangeAttack(0);
        bd6.setType(0);
        bd6.setCapacity(5);

        BuildingModel bd7 = new BuildingModel();
        bd7.setName("Gold Mine");
        bd7.setDame(0);
        bd7.setHp(50);
        bd7.setPrice(10.0);
        bd7.setSpeed(0);
        bd7.setBuildTime(10);
        bd7.setType(0);
        bd7.setRangeAttack(0);
        bd7.setType(0);
        bd7.setCapacity(5);

        BuildingModel bd8 = new BuildingModel();
        bd8.setName("Gold Storage");
        bd8.setDame(0);
        bd8.setHp(50);
        bd8.setPrice(10.0);
        bd8.setSpeed(0);
        bd8.setBuildTime(10);
        bd8.setType(0);
        bd8.setRangeAttack(0);
        bd8.setType(0);
        bd8.setCapacity(5);

        BuildingModel bd9 = new BuildingModel();
        bd9.setName("Labratory");
        bd9.setDame(0);
        bd9.setHp(50);
        bd9.setPrice(10.0);
        bd9.setSpeed(0);
        bd9.setBuildTime(10);
        bd9.setType(0);
        bd9.setRangeAttack(0);
        bd9.setType(0);
        bd9.setCapacity(5);

        BuildingModel bd10 = new BuildingModel();
        bd10.setName("Motar");
        bd10.setDame(0);
        bd10.setHp(50);
        bd10.setPrice(10.0);
        bd10.setSpeed(0);
        bd10.setBuildTime(10);
        bd10.setType(0);
        bd10.setRangeAttack(0);
        bd10.setType(0);
        bd10.setCapacity(5);

        BuildingModel bd11 = new BuildingModel();
        bd11.setName("Obstacle");
        bd11.setDame(0);
        bd11.setHp(50);
        bd11.setPrice(10.0);
        bd11.setSpeed(0);
        bd11.setBuildTime(10);
        bd11.setType(0);
        bd11.setRangeAttack(0);
        bd11.setType(0);
        bd11.setCapacity(5);

        BuildingModel bd12 = new BuildingModel();
        bd12.setName("Townhall");
        bd12.setDame(0);
        bd12.setHp(50);
        bd12.setPrice(10.0);
        bd12.setSpeed(0);
        bd12.setBuildTime(10);
        bd12.setType(0);
        bd12.setRangeAttack(0);
        bd12.setType(0);
        bd12.setCapacity(5);

        BuildingModel bd13 = new BuildingModel();
        bd13.setName("Wall");
        bd13.setDame(0);
        bd13.setHp(50);
        bd13.setPrice(10.0);
        bd13.setSpeed(0);
        bd13.setBuildTime(10);
        bd13.setType(0);
        bd13.setRangeAttack(0);
        bd13.setType(0);
        bd13.setCapacity(5);

        buildingService.addBuilding(bd1);
        buildingService.addBuilding(bd2);
        buildingService.addBuilding(bd3);
        buildingService.addBuilding(bd4);
        buildingService.addBuilding(bd5);
        buildingService.addBuilding(bd6);
        buildingService.addBuilding(bd7);
        buildingService.addBuilding(bd8);
        buildingService.addBuilding(bd9);
        buildingService.addBuilding(bd10);
        buildingService.addBuilding(bd11);
        buildingService.addBuilding(bd12);
        buildingService.addBuilding(bd13);
    }

    private void initTrooperUser() {
        //qui
        for (int i = 1; i <= 3; i++) {
            trooperService.increaseTrooperByUserID(1, i, 3);
        }
        //nghia
        for (int i = 1; i <= 3; i++) {
            trooperService.increaseTrooperByUserID(2, i, 3);
        }
        //luan
        for (int i = 1; i <= 3; i++) {
            trooperService.increaseTrooperByUserID(3, i, 3);
        }
    }

    private void initBuildingUser() {
        //qui
        for (int i = 1; i <= 13; i++) {
            buildingService.increaseBuildingByUserID(1, i, 3);
        }
        //nghia
        for (int i = 1; i <= 13; i++) {
            buildingService.increaseBuildingByUserID(2, i, 3);
        }
        //luan
        for (int i = 1; i <= 13; i++) {
            buildingService.increaseBuildingByUserID(3, i, 3);
        }
    }

    private void initAccount() {
        UserModel qui = new UserModel();

        qui.setUserName("letuongqui");
        qui.setPassWord("1");
        qui.setCoin(100000);
        qui.setLevel(1);
        qui.setCurrentExperience(0);
        qui.setUrlMap("/upload/map-1.DAT");

        UserModel nghia = new UserModel();
        nghia.setUserName("nghiatq");
        nghia.setPassWord("1");
        nghia.setCoin(100000);
        nghia.setLevel(1);
        nghia.setCurrentExperience(0);
        nghia.setUrlMap("/upload/map-2.DAT");

        UserModel luan = new UserModel();
        luan.setUserName("lecongluan");
        luan.setPassWord("1");
        luan.setCoin(100000);
        luan.setLevel(1);
        luan.setCurrentExperience(0);
        luan.setUrlMap("/upload/map-3.DAT");

        userService.addUserModel(qui);
        userService.addUserModel(nghia);
        userService.addUserModel(luan);
    }
}
