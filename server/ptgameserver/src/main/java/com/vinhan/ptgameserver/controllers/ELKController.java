/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import com.vinhan.ptgameserver.utils.ReponseUtils;
import ga.log4j.GA;
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
    private int i = 0;

    @ApiOperation(value = "Test Log")
    @GetMapping(value = "/log", produces = "application/json")
    public String initData() {
        try {
            String logfile = "type2 teso" + (i % 5);
            i++;

//            System.err.println(logfile);
//            LOGGER.error(logfile);
            GA.app.error(logfile);
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }

    @ApiOperation(value = "Test Log 2")
    @GetMapping(value = "/log-2", produces = "application/json")
    public String initData2() {
        try {
            String logfile = "type1 teso  struct" + (i % 5);
            i++;
//            System.err.println(logfile);
//            LOGGER.error(logfile);
            GA.ewallet.error(logfile);
        } catch (Exception e) {

        }
        return ReponseUtils.ServerError();
    }
}
