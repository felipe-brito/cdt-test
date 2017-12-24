package br.com.sgt.main;

import br.com.sgt.config.ConfigCDI;
import org.quartz.SchedulerException;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 13/12/2017
 *
 */
public class App {

    public static void main(String[] args) throws SchedulerException {

        ConfigCDI configCDI = new ConfigCDI();
        configCDI.init();
    }

}
