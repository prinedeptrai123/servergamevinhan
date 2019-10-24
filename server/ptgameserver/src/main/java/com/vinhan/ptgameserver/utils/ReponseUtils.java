/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import static com.vinhan.ptgameserver.config.ConfigInfo.MAPPER;
import com.vinhan.ptgameserver.constant.ErrorCode;
import com.vinhan.ptgameserver.constant.StatusCode;

/**
 *
 * @author Qui
 */
public class ReponseUtils {

    public static String success(StatusCode code, JsonNode body) {
        ObjectNode node = MAPPER.createObjectNode();
        node.put(StatusCode.class.getSimpleName(), code.getValue());
        node.set("Response", body);
        return node.toString();
    }

    public static String ServerError() {
        ObjectNode node = MAPPER.createObjectNode();
        node.put(StatusCode.class.getSimpleName(), ErrorCode.SERVER_ERROR.getValue());
        node.put("Response", ErrorCode.SERVER_ERROR.name());
        return node.toString();
    }

    public static String NotFound() {
        ObjectNode node = MAPPER.createObjectNode();
        node.put(StatusCode.class.getSimpleName(), 404);
        node.set("Response", null);
        return node.toString();
    }

    public static String succesDone() {
        ObjectNode node = MAPPER.createObjectNode();
        node.put(StatusCode.class.getSimpleName(), 200);
        node.put("Response", "SUCCESS");
        return node.toString();
    }

    public static String Failure() {
        ObjectNode node = MAPPER.createObjectNode();
        node.put(StatusCode.class.getSimpleName(), 400);
        node.put("Response", "FAILURE");
        return node.toString();
    }

    public static String Invalid() {
        ObjectNode node = MAPPER.createObjectNode();
        node.put(StatusCode.class.getSimpleName(), 400);
        node.put("Response", "INVALID");
        return node.toString();
    }
    
    public static String Duplicate() {
        ObjectNode node = MAPPER.createObjectNode();
        node.put(StatusCode.class.getSimpleName(), 400);
        node.put("Response", "DUPLICATE");
        return node.toString();
    }

}
