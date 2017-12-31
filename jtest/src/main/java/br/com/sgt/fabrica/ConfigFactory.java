package br.com.sgt.fabrica;

import br.com.sgt.util.Propriedades;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.swing.JFileChooser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 14/12/2017
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigFactory {

    /**
     * Produz instâncias de {@link Scheduler}
     *
     * @return
     */
    @Produces
    public Scheduler scheduler() {
        try {
            return new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException ex) {
            //lançar exceção personalizada
        }
        return null;
    }

    /**
     * Produz instâncias de {@link JFileChooser}
     *
     * @return
     */
    @Produces
    public JFileChooser producesJFileChooser() {
        return new JFileChooser();
    }

    /**
     * Produz uma instância da classe que contém as propriedades da aplicação,
     * as propriedades ficam fora do contexto, assim evitando o build do projeto
     * para que novas propriedades sejam carregadas.
     *
     * @return
     */
    @Produces
    public Propriedades producesSGTProperties() {
        try {
            //ajustar para leitura fora do contexto da aplicação
            List<String> json = Files.readAllLines(Paths.get(getClass().getResource("/properties/propriedades.json").toURI()));

            StringBuilder sb = new StringBuilder();

            json.forEach((str) -> {
                sb.append(str);
            });

            return new Gson().fromJson(sb.toString(), Propriedades.class);

        } catch (IOException | URISyntaxException ex) {
            return null;
        }
    }
    
}
