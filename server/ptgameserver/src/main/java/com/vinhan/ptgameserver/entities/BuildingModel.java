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
@Table(name = "db_building")
public class BuildingModel extends BaseModel{
    String name;
    Double price;
    double buildTime;
    int type;
    int hp;
    double dame;
    //sức chứa
    double capacity;
    double speed;
    //tầm bắn
    double rangeAttack;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<MUserBuilding> mUserBuildings;
}
