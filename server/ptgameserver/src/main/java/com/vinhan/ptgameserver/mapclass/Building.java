/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.mapclass;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author Qui
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "Building", description = "Building")
public class Building {
    int id;
    String name;
    Double price;
    double buildTime;
    int type;
    int hp;
    double dame;
    //sức chứa
    double capacity;
    //tốc độ sản xuất
    double speed;
    double rangeAttack;
}
