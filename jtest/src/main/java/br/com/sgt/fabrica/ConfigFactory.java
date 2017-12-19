package br.com.sgt.fabrica;

import javax.enterprise.inject.Produces;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 14/12/2017
 *
 */
public class ConfigFactory {

    @Produces
    public Scheduler scheduler() {
        try {
            return new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
        return null;
    }

}
