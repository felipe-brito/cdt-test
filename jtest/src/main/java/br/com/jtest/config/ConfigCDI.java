package br.com.jtest.config;

import br.com.jtest.application.Application;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 13/12/2017
 *
 */
public class ConfigCDI {

    public void init() {

        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        Application application = container.select(Application.class).get();
        application.run();
        weld.shutdown();

    }

}
