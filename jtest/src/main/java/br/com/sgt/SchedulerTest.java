/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgt;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 13/12/2017
 *
 */
public class SchedulerTest implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(context.getTrigger().getJobKey().getName());
    }

}
