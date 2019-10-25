/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver;

import com.vinhan.ptgameserver.config.ConfigInfo;
import com.vinhan.ptgameserver.storage.StorageProperties;
import com.vinhan.ptgameserver.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author Qui
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {

    public static void main(String[] args) {
        System.err.println("==============>" + ConfigInfo.SPRING_BOOT_CONFIG);

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(Main.class)
                .properties("spring.config.location:" + ConfigInfo.SPRING_BOOT_CONFIG)
                .build().run(args);
        System.err.println("start at: " + ConfigInfo.STATIC_SERVER);
    }

}
