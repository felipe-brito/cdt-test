package br.com.jtest.agendador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 */
@Singleton
public class AgendadorSingleton {

    @Inject
    private Scheduler scheduler;

//    public static AgendadorSingleton getInstance() {
//        return SchedulerServiceSingletonHolder.INSTANCE;
//    }
//
//    private static class SchedulerServiceSingletonHolder {
//
//        private static final AgendadorSingleton INSTANCE = new AgendadorSingleton();
//    }
    public void agendar(AgendadorWrapper wrapper) {
        try {
            JobDetail jobDetail = getJobDetail(wrapper);
            Trigger trigger = getTrigger(wrapper);
            this.scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException ex) {
            Logger.getLogger(AgendadorSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void iniciar() {

        try {
            this.scheduler.start();
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
    }

    public void parar() {

        try {
            this.scheduler.shutdown();
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
    }

    public void remover(String jobKey) {

        try {
            this.scheduler.deleteJob(JobKey.jobKey(jobKey));
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
    }

    public void removerTodos() {

        try {
            this.scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup())
                    .stream()
                    .forEach((trigger) -> {
                        remover(trigger.getName());
                    });
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
    }

    public void pausarTodos() {
        try {
            this.scheduler.pauseAll();
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
    }

    public void pausar(String jobKey) {
        try {
            this.scheduler.pauseJob(JobKey.jobKey(jobKey));
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
    }

    public void continuar(String jobKey) {
        try {
            this.scheduler.resumeJob(JobKey.jobKey(jobKey));
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
    }

    public void continuarTodos() {
        try {
            this.scheduler.resumeAll();
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
    }

    private JobDetail getJobDetail(AgendadorWrapper wrapper) {
        return JobBuilder
                .newJob(wrapper.getClasse())
                .withIdentity(wrapper.getNomeAgendador())
                .build();
    }

    private Trigger getTrigger(AgendadorWrapper wrapper) {
        return TriggerBuilder
                .newTrigger()
                .withIdentity(wrapper.getNomeGatilho())
                .withSchedule(CronScheduleBuilder.cronSchedule(wrapper.getExpressaoCron()))
                .build();
    }

}
