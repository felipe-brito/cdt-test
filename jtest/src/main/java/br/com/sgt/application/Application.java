package br.com.sgt.application;

import br.com.sgt.gui.TelaPrincipalUI;
import javax.inject.Inject;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 13/12/2017
 *
 */
public class Application {

    @Inject
    private TelaPrincipalUI inicioUI;
    
    public void run() {

        BasicConfigurator.configure();
        inicioUI.init();

    }

}
