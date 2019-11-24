/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.services;

import com.vinhan.ptgameserver.db.StoreRepository;
import com.vinhan.ptgameserver.entities.BuildingModel;
import com.vinhan.ptgameserver.entities.MUserBuilding;
import com.vinhan.ptgameserver.entities.MUserTrooper;
import com.vinhan.ptgameserver.entities.TrooperModel;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.mapclass.Building;
import com.vinhan.ptgameserver.mapclass.Trooper;
import com.vinhan.ptgameserver.mapclass.UserBuilding;
import com.vinhan.ptgameserver.mapclass.UserTrooper;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Qui
 */
@Component
public class BuildingService {
    @Autowired
    private StoreRepository storeRepository;

    private final DozerBeanMapper mapper = new DozerBeanMapper();
    
    public void addBuilding(BuildingModel buidling) {
        try {
           storeRepository.save(buidling);
        } catch (Exception e) {
        }
    }

    public List<Building> getAllBuilding() {
        List<Building> result = new ArrayList<Building>();
        try {
            List<BuildingModel> db = storeRepository.query(BuildingModel.class).findList();
            if (db != null) {
                for (BuildingModel buildingModel : db) {
                    Building building = mapper.map(buildingModel, Building.class);
                    result.add(building);
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public List<UserBuilding> getBuildingByUserID(int userID) {
        List<UserBuilding> result = new ArrayList<UserBuilding>();
        try {
            List<MUserBuilding> db = storeRepository.query(MUserBuilding.class)
                    .where()
                    .eq("user_model_id", userID)
                    .findList();
            if (db != null) {
                for (MUserBuilding mUserBuilding : db) {
                    Building building = mapper.map(mUserBuilding.getBuildingModel(), Building.class);
                    UserBuilding userBuilding = new UserBuilding();
                    userBuilding.setCount(mUserBuilding.getCount());
                    userBuilding.setBuilding(building);
                    result.add(userBuilding);
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    public boolean deacreaseBuildingByUserID(int userID, int buildingID, int count) {
        try {
            MUserBuilding db = storeRepository.query(MUserBuilding.class)
                    .where()
                    .and()
                    .eq("user_model_id", userID)
                    .eq("building_model_id", buildingID)
                    .endAnd()
                    .findOne();
            if (db != null) {
                System.out.println("com.vinhan.ptgameserver.services.BuildingService.deacreaseBuildingByUserID()");
                int currentBuilding = db.getCount();
                if (currentBuilding - count >= 0) {
                    db.setCount(currentBuilding - count);
                    storeRepository.update(db);
                    return true;
                }
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean increaseBuildingByUserID(int userID, int buildingID, int count) {
        try {
            MUserBuilding db = storeRepository.query(MUserBuilding.class)
                    .where()
                    .and()
                    .eq("user_model_id", userID)
                    .eq("building_model_id", buildingID)
                    .endAnd()
                    .findOne();
            if (db != null) {
                int currentBuilding = db.getCount();
                db.setCount(count + currentBuilding);
                storeRepository.save(db);
                return true;
            } else {
                MUserBuilding dbsave = new MUserBuilding();
                dbsave.setCount(count);
                
                UserModel userModel= new UserModel();
                userModel.setId(userID);
                dbsave.setUserModel(userModel);
                
                BuildingModel buildingModel =new BuildingModel();
                buildingModel.setId(buildingID);
                dbsave.setBuildingModel(buildingModel);
                
                storeRepository.save(dbsave);
            }
        } catch (Exception e) {
        }

        return false;
    }
}
