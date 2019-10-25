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

    //config ebean
    public static final String EBEAN_GENERATE = Config.getParam("ebean", "dll_generate");
    public static final String EBEAN_RUN = Config.getParam("ebean", "dll_run");
    public static final String EBEAN_USERNAME = Config.getParam("ebean", "db_username");
    public static final String EBEAN_PASSWORD = Config.getParam("ebean", "db_password");
    public static final String EBEAN_URL = Config.getParam("ebean", "db_url");
    public static final String EBEAN_DRIVER = Config.getParam("ebean", "db_driver");
    public static final String EBEAN_ENT_PACKAGE = Config.getParam("ebean", "search_package");

    //config upload
    public static final String UPLOAD_DIR = Config.getParam("param", "upload_dir");
    public static final String UPLOAD_PATH = Config.getParam("upload_path", "folder");
    public static final String UPLOAD_FOLDER = Config.getParam("upload_folder", "name");
    public static final String UPLOAD_URL = Config.getParam("upload_url", "upload_name");

    public static final String URL_FORMAT = "map-%s.%s";

    public static final String DEFAULT_MAP = "map-0.DAT";
    public static final String URL_DEFAULT_MAP = UPLOAD_FOLDER + DEFAULT_MAP;

}
