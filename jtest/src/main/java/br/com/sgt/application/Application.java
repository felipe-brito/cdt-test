package br.com.sgt.application;

import br.com.sgt.gui.InicioUI;
import javax.inject.Inject;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 13/12/2017
 *
 */
public class Application {

//    @Inject
//    private AgendadorSingleton agendadorServiceSingleton;
//
//    @Inject
//    private AgendadorWrapper wrapper;
//
    @Inject
    private InicioUI inicioUI;
    
    public void run() {

//        wrapper.setExpressaoCron("0/10 * * * * ?");
//        wrapper.setClasse(SchedulerTest.class);
//        wrapper.setNomeAgendador("TesteAgendador");
//        wrapper.setNomeGatilho("TesteGatilho");
//
//        agendadorServiceSingleton.agendar(wrapper);
//        agendadorServiceSingleton.iniciar();

        inicioUI.init();

    }

}
