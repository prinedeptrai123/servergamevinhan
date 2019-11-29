/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.vinhan.ptgameserver.utils.ReponseUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Qui
 */
//@RestController
//@RequestMapping("/api/elk")
public class ELKController {

    private static final Logger LOGGER = LogManager.getLogger(ELKController.class);

    @ApiOperation(value = "Test Log")
    @GetMapping(value = "/log", produces = "application/json")
    public String initData() {
        try {
            String logfile = "testing log";
            System.err.println(logfile);
            LOGGER.error(logfile);
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }
}
