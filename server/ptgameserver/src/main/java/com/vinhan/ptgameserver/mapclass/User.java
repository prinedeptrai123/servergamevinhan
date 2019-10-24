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
@ApiModel(value = "User", description = "user")
public class User {

    @ApiModelProperty(example = "1",readOnly = true)
    int id;

    @ApiModelProperty(example = "lecongluan")
    String userName;

    @ApiModelProperty(example = "123456789")
    String passWord;

    @ApiModelProperty(example = "/upload/aaa.bin")
    String urlMap;
}
