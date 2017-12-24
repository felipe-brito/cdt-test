package br.com.sgt.agendador;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @param <T>
 * @date 13/12/2017
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"nomeAgendador", "nomeGatilho"})
public class AgendadorWrapper<T> implements Serializable {

    @NotNull(message = "A classe de serviço do JOB deve não pode ser nula.")
    private Class<T> classe;

    @NotNull(message = "Nome do Scheduler não pode ser nulo.")
    private String nomeAgendador;

    @NotNull(message = "Nome do Trigger não pode ser nulo")
    private String nomeGatilho;

    @NotNull(message = "A expressao Cron de tempo não pode ser nula.")
    private String expressaoCron;

}
