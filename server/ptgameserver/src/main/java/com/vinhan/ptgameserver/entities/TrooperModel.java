/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@Table(name = "db_trooper")
public class TrooperModel extends BaseModel {
    String name;
    Double price;
    double dame;
    double spornTime;
    double speed;
    int type;
    int hp;
    double rangeAttack;
    double speedAttack;
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<MUserTrooper> mUserTroopers;
    
}
