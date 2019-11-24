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
@ApiModel(value = "Trooper", description = "Trooper")
public class Trooper {
    int id;
    String name;
    Double price;
    double dame;
    double spornTime;
    double speed;
    //Đánh xa đánh gần gì đấy
    int type;
    int hp;
    //Tầm bắn
    double rangeAttack;
    //tốc độ bắn
    double speedAttack;
}
