/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.mapclass;

import com.vinhan.ptgameserver.entities.MUserTrooper;
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
@ApiModel(value = "UserTrooper", description = "UserTrooper")
public class UserTrooper {
    Trooper trooper;
    int count;
}
