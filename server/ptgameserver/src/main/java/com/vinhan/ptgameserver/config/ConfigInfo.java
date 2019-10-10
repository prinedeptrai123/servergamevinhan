/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teso.framework.common.Config;

/**
 *
 * @author Qui
 */
public class ConfigInfo {

    public static final String SPRING_BOOT_CONFIG = Config.getParam("spring_boot", "conf_path");
    public static final String STATIC_SERVER = Config.getParam("static_server", "root_url");

    public static final ObjectMapper MAPPER = new ObjectMapper();
}
