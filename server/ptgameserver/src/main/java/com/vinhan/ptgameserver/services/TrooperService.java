/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.services;

import com.vinhan.ptgameserver.db.StoreRepository;
import com.vinhan.ptgameserver.entities.TrooperModel;
import com.vinhan.ptgameserver.entities.MUserTrooper;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.mapclass.Trooper;
import com.vinhan.ptgameserver.mapclass.UserTrooper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Qui
 */
@Component
public class TrooperService {

    @Autowired
    private StoreRepository storeRepository;

    private final DozerBeanMapper mapper = new DozerBeanMapper();

    public void addTrooper(TrooperModel trooper) {
        try {
           storeRepository.save(trooper);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Trooper> getAllTrooper() {
        List<Trooper> result = new ArrayList<Trooper>();
        try {
            List<TrooperModel> db = storeRepository.query(TrooperModel.class).findList();
            if (db != null) {
                for (TrooperModel trooperModel : db) {
                    Trooper trooper = mapper.map(trooperModel, Trooper.class);
                    result.add(trooper);
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public List<UserTrooper> getTrooperByUserID(int userID) {
        List<UserTrooper> result = new ArrayList<UserTrooper>();
        try {
            List<MUserTrooper> db = storeRepository.query(MUserTrooper.class)
                    .where()
                    .eq("user_model_id", userID)
                    .findList();
            if (db != null) {
                for (MUserTrooper muserTrooper : db) {
                    Trooper trooper = mapper.map(muserTrooper.getTrooperModel(), Trooper.class);
                    UserTrooper userTrooper = new UserTrooper();
                    userTrooper.setCount(muserTrooper.getCount());
                    userTrooper.setTrooper(trooper);
                    result.add(userTrooper);
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    public boolean deacreaseTrooperByUserID(int userID, int trooperID, int count) {
        try {
            MUserTrooper db = storeRepository.query(MUserTrooper.class)
                    .where()
                    .and()
                    .eq("user_model_id", userID)
                    .eq("trooper_model_id", trooperID)
                    .endAnd()
                    .findOne();
            if (db != null) {
                int currentTrooper = db.getCount();
                if (currentTrooper - count >= 0) {
                    db.setCount(currentTrooper - count);
                    storeRepository.update(db);
                    return true;
                }
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean increaseTrooperByUserID(int userID, int trooperID, int count) {
        try {
            MUserTrooper db = storeRepository.query(MUserTrooper.class)
                    .where()
                    .and()
                    .eq("user_model_id", userID)
                    .eq("trooper_model_id", trooperID)
                    .endAnd()
                    .findOne();
            if (db != null) {
                int currentTrooper = db.getCount();
                db.setCount(count + currentTrooper);
                storeRepository.save(db);
                return true;
            } else {
                MUserTrooper dbsave = new MUserTrooper();
                dbsave.setCount(count);

                UserModel userModel = new UserModel();
                userModel.setId(userID);
                dbsave.setUserModel(userModel);

                TrooperModel trooperModel = new TrooperModel();
                trooperModel.setId(trooperID);
                dbsave.setTrooperModel(trooperModel);

                storeRepository.save(dbsave);
            }
        } catch (Exception e) {
        }

        return false;
    }
}
