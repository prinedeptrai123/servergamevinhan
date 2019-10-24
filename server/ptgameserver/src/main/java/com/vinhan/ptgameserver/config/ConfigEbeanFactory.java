/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.config;

import static com.vinhan.ptgameserver.config.ConfigInfo.EBEAN_DRIVER;
import static com.vinhan.ptgameserver.config.ConfigInfo.EBEAN_ENT_PACKAGE;
import static com.vinhan.ptgameserver.config.ConfigInfo.EBEAN_GENERATE;
import static com.vinhan.ptgameserver.config.ConfigInfo.EBEAN_PASSWORD;
import static com.vinhan.ptgameserver.config.ConfigInfo.EBEAN_RUN;
import static com.vinhan.ptgameserver.config.ConfigInfo.EBEAN_URL;
import static com.vinhan.ptgameserver.config.ConfigInfo.EBEAN_USERNAME;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import java.util.Properties;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author Qui
 */
@Component
public class ConfigEbeanFactory implements FactoryBean<EbeanServer> {

    private final Logger LOGGER = LogManager.getLogger(ConfigEbeanFactory.class);

    @Override
    public EbeanServer getObject() throws Exception {
        try {
            ServerConfig cfg = new ServerConfig();
            Properties properties = new Properties();
            properties.put("ebean.db.ddl.generate", EBEAN_GENERATE);
            properties.put("ebean.db.ddl.run", EBEAN_RUN);
            properties.put("datasource.db.username", EBEAN_USERNAME);
            properties.put("datasource.db.password", EBEAN_PASSWORD);
            properties.put("datasource.db.databaseUrl", EBEAN_URL);
            properties.put("datasource.db.databaseDriver", EBEAN_DRIVER);
            properties.put("ebean.search.packages", EBEAN_ENT_PACKAGE);
            cfg.loadFromProperties(properties);

            return EbeanServerFactory.create(cfg);
        } catch (Exception e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    @Override
    public Class<?> getObjectType() {
        return EbeanServer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
