/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teso.framework.utils;

import com.google.gson.*;

/**
 *
 * @author huynxt
 *
 */
public class FirebaseMsg {

    private String message;
    private String title;
    private String icon;
    private String color;
    private String sound;
    private String tag;
    private String clickAction;
    private String dataContent;

    public FirebaseMsg() {
    }

    public JsonObject getDataObj(String dataContent) {
        JsonObject objData = new JsonObject();
        objData.addProperty("contents", dataContent);
        return objData;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getClickAction() {
        return clickAction;
    }

    public void setClickAction(String clickAction) {
        this.clickAction = clickAction;
    }

    public JsonElement createNotificationObj(String title, String body) {
        JsonObject obj = new JsonObject();

        obj.addProperty("body", body);
        obj.addProperty("title", title);
        obj.addProperty("badge", "1");

        return obj;
    }

    public JsonElement createNotificationObj(String title, String body, String color, String sound, String tag, String clickAction) {
        JsonObject obj = new JsonObject();

        obj.addProperty("body", body);
        obj.addProperty("title", title);
        obj.addProperty("badge", "1");
        obj.addProperty("color", color);
        obj.addProperty("sound", sound);
        obj.addProperty("tag", tag);
        obj.addProperty("click_action", clickAction);

        return obj;
    }

    public JsonElement createDataObj(String data) {
        JsonObject obj = StringUtils.isEmpty(data) ? null : new JsonParser().parse(data).getAsJsonObject();
        return obj;
    }

    public JsonObject createJsonPushTopicObj(String topic) {
        JsonObject obj = new JsonObject();

        obj.addProperty("to", "/topics/" + topic);
        obj.add("data", this.getDataObj(this.getDataContent()));
        obj.add("notification", this.createNotificationObj(this.getTitle(), this.getMessage()));

        return obj;
    }

    public static JsonObject createJsonSubscribeTopicObj(String[] lstKeypush, String topic) {
        JsonObject obj = new JsonObject();

        JsonArray jsonArrayId = JSONUtil.DeSerialize(JSONUtil.Serialize(lstKeypush), JsonArray.class);
        obj.addProperty("to", "/topics/" + topic);
        obj.add("registration_tokens", jsonArrayId);

        return obj;
    }

    public JsonObject createJsonPushDeviceObj(String[] lstKeypush) {
        JsonObject obj = new JsonObject();

        JsonArray arrId = JSONUtil.DeSerialize(JSONUtil.Serialize(lstKeypush), JsonArray.class);
        obj.add("registration_ids", arrId);
        obj.add("data", this.createDataObj(this.getDataContent()));
        obj.add("notification", this.createNotificationObj(this.getTitle(), this.getMessage(), this.getColor(), this.getSound(), this.getTag(), this.getClickAction()));

        return obj;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
