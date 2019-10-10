/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Qui
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MapController {
    
    @GetMapping(value = "/map/{id}", produces = "application/json")
    public String getMapOfUser(@PathVariable(name = "id") int id) {
        return "qui";
    }
}
