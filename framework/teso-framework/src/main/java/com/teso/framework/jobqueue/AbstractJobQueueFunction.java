/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teso.framework.jobqueue;

import com.teso.framework.gearman.JobEnt;

/**
 *
 * @author huynxt
 */
public abstract class AbstractJobQueueFunction {
    public abstract JobQueueResult executeFunction(JobEnt job);
}
