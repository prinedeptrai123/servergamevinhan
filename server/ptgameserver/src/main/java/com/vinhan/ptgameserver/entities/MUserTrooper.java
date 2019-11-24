/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author Qui
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "db_user_trooper")
public class MUserTrooper extends BaseModel{
    @ManyToOne
    @Lazy(value = false)
    private UserModel userModel;
    
    @ManyToOne
    @Lazy(value = false)
    private TrooperModel trooperModel;
    
    int count;
    
    
}
