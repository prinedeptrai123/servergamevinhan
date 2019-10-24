/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author Qui
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "db_user")
public class UserModel extends BaseModel {
    String userName;
    String passWord;
    String urlMap;
}
