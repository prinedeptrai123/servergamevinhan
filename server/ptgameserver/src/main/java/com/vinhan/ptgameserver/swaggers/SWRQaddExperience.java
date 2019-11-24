/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.swaggers;

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
@ApiModel(value = "SWRQaddExperience", description = "request")
public class SWRQaddExperience {
    @ApiModelProperty(example = "1")
    String userID;

    @ApiModelProperty(example = "50")
    String exp;
}
