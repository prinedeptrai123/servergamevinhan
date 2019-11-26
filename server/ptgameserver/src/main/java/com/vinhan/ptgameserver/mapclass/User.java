/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.mapclass;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author Qui
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    int id;
    String userName;
    String passWord;
    String urlMap;
    double currentExperience;
    double maxExperience;
    int level;
    int coin;
    int trooper;
    int building;
    int dynamon;
}
