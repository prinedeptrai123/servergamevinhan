/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.entities;

import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import java.time.Instant;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 *
 * @author Qui
 */
@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @WhenCreated
    Instant createdTime;
    @WhenModified
    Instant updateTime;
    boolean deleteFlag;
    boolean defaultFlag;
}
