/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.services;

import static com.vinhan.ptgameserver.config.ConfigInfo.LEVEL_EXP;
import static com.vinhan.ptgameserver.config.ConfigInfo.LEVEL_EXP_UP_RATIO;
import com.vinhan.ptgameserver.db.StoreRepository;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.mapclass.User;
import io.ebean.Model;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.eclipse.jdt.internal.compiler.ast.TrueLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Qui
 */
@Component
public class UserService {

    @Autowired
    private StoreRepository storeRepository;

    private final DozerBeanMapper mapper = new DozerBeanMapper();

    public UserModel login(String userName, String passWord) {
        UserModel result = null;
        try {
            result = storeRepository.query(UserModel.class)
                    .where()
                    .and()
                    .eq("user_name", userName)
                    .eq("pass_word", passWord)
                    .findOne();
        } catch (Exception e) {

        }
        return result;
    }

    public boolean addUserModel(UserModel userdb) {
        try {
            storeRepository.save(userdb);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean isExistUserName(String userName) {
        try {
            UserModel db = storeRepository.query(UserModel.class)
                    .where()
                    .eq("user_name", userName)
                    .findOne();
            if (db != null) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean isExistId(int userId) {
        try {
            UserModel db = storeRepository.findById(UserModel.class, userId);
            if (db != null) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public List<User> getAllUser() {
        List<User> result = new ArrayList<User>();
        try {
            List<UserModel> db = storeRepository.query(UserModel.class).findList();
            if (db.size() > 0) {
                for (UserModel user : db) {
                    User rs = mapper.map(user, User.class);
                    result.add(rs);
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    public boolean updateUrlMap(int userId, String url) {
        try {
            UserModel db = storeRepository.findById(UserModel.class, userId);
            if (db != null) {
                db.setUrlMap(url);
                storeRepository.save(db);
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public UserModel levelUP(int userID, double experience) {
        try {
            UserModel db = storeRepository.findById(UserModel.class, userID);
            if (db != null) {
                double currentExperience = db.getCurrentExperience();
                int currentLevel = db.getLevel();
                double levelUpEXP = LEVEL_EXP *Math.pow((1+LEVEL_EXP_UP_RATIO), currentLevel-1);

                if (currentExperience + experience < levelUpEXP) {
                    db.setCurrentExperience(currentExperience + experience);
                } else {
                    db.setLevel(currentLevel + 1);
                    db.setCurrentExperience(currentExperience + experience - levelUpEXP);
                }
                storeRepository.save(db);

                return db;
            }
        } catch (Exception e) {

        }

        return null;
    }

    public UserModel increaseCoin(int userID, int coin) {
        try {
            UserModel db = storeRepository.findById(UserModel.class, userID);
            if (db != null) {
                int currenCoin = db.getCoin();
                db.setCoin(currenCoin + coin);
                storeRepository.save(db);
                return db;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public UserModel decreaseCoin(int userID, int coin) {
        try {
            UserModel db = storeRepository.findById(UserModel.class, userID);
            if (db != null) {
                int currenCoin = db.getCoin();
                if (currenCoin - coin > 0) {
                    db.setCoin(currenCoin - coin);
                    storeRepository.save(db);
                    return db;
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

}
