/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import static com.vinhan.ptgameserver.config.ConfigInfo.MAPPER;
import com.vinhan.ptgameserver.entities.UserModel;
import com.vinhan.ptgameserver.mapclass.Building;
import com.vinhan.ptgameserver.mapclass.Trooper;
import com.vinhan.ptgameserver.mapclass.User;
import com.vinhan.ptgameserver.mapclass.UserBuilding;
import com.vinhan.ptgameserver.mapclass.UserTrooper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Qui
 */
public class ConverterUtils {

    public static ObjectNode returnMap(String map) {
        ObjectNode node = MAPPER.createObjectNode();
        node.put("map", map);
        return node;
    }

    public static ObjectNode returnUser(UserModel user) {
        ObjectNode node = MAPPER.createObjectNode();
        node.put("id", user.getId());
        node.put("username", user.getUserName());
        node.put("map", user.getUrlMap());
        node.put("coin", user.getCoin());
        node.put("level", user.getLevel());
        node.put("exp", user.getCurrentExperience());
        return node;
    }

    public static ObjectNode returnUser(User user) {
        ObjectNode node = MAPPER.createObjectNode();
        node.put("password", user.getPassWord());
        node.put("id", user.getId());
        node.put("username", user.getUserName());
        node.put("map", user.getUrlMap());
        node.put("coin", user.getCoin());
        node.put("level", user.getLevel());
        node.put("exp", user.getCurrentExperience());
        node.put("dynamon", user.getDynamon());
        node.put("trooper", user.getTrooper());
        node.put("maxExp", user.getMaxExperience());
        node.put("building", user.getBuilding());

        return node;
    }

    public static JsonNode request2Json(HttpServletRequest request) {
        try {
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            return MAPPER.readTree(body);
        } catch (IOException ex) {
            Logger.getLogger(ConverterUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayNode returnListUser(List<User> list) {
        ArrayNode node = MAPPER.createArrayNode();
        for (User user : list) {
            node.add(returnUser(user));
        }
        return node;
    }

    public static ObjectNode returnTrooper(Trooper trooper) {
        ObjectNode node = MAPPER.createObjectNode();
        node.put("id", trooper.getId());
        node.put("name", trooper.getName());
        node.put("dame", trooper.getDame());
        node.put("coin", trooper.getHp());
        node.put("price", trooper.getPrice());
        node.put("speed", trooper.getSpeed());
        node.put("spornTime", trooper.getSpornTime());
        node.put("type", trooper.getType());
        node.put("rangeAttack", trooper.getRangeAttack());
        node.put("speedAttack", trooper.getSpeedAttack());

        return node;
    }

    public static ArrayNode returnListTrooper(List<Trooper> list) {
        ArrayNode node = MAPPER.createArrayNode();
        for (Trooper trooper : list) {
            node.add(returnTrooper(trooper));
        }
        return node;
    }

    public static ObjectNode returnUserTrooper(UserTrooper userTrooper) {
        ObjectNode node = MAPPER.createObjectNode();
        node.put("trooper", returnTrooper(userTrooper.getTrooper()));
        node.put("count", userTrooper.getCount());

        return node;
    }

    public static ArrayNode returnListUserTrooper(List<UserTrooper> list) {
        ArrayNode node = MAPPER.createArrayNode();
        for (UserTrooper userTrooper : list) {
            node.add(returnUserTrooper(userTrooper));
        }
        return node;
    }

    public static ObjectNode returnBuilding(Building building) {
        ObjectNode node = MAPPER.createObjectNode();
        node.put("id", building.getId());
        node.put("name", building.getName());
        node.put("dame", building.getDame());
        node.put("coin", building.getHp());
        node.put("price", building.getPrice());
        node.put("speed", building.getSpeed());
        node.put("buildTime", building.getBuildTime());
        node.put("type", building.getType());
        node.put("capacity", building.getCapacity());
        node.put("rangeAttack", building.getRangeAttack());

        return node;
    }

    public static ArrayNode returnListBuilding(List<Building> list) {
        ArrayNode node = MAPPER.createArrayNode();
        for (Building building : list) {
            node.add(returnBuilding(building));
        }
        return node;
    }

    public static ObjectNode returnUserBuilding(UserBuilding userBuilding) {
        ObjectNode node = MAPPER.createObjectNode();
        node.put("building", returnBuilding(userBuilding.getBuilding()));
        node.put("count", userBuilding.getCount());

        return node;
    }

    public static ArrayNode returnListUserBuilding(List<UserBuilding> list) {
        ArrayNode node = MAPPER.createArrayNode();
        for (UserBuilding userBuilding : list) {
            node.add(returnUserBuilding(userBuilding));
        }
        return node;
    }
}
