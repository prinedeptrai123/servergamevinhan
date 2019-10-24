/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import static com.vinhan.ptgameserver.config.ConfigInfo.MAPPER;
import com.vinhan.ptgameserver.entities.UserModel;
import java.io.IOException;
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
}
