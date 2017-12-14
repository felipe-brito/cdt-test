package br.com.jtest.agendador;

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

    @NotNull
    private Class<T> classe;

    @NotNull
    private String nomeAgendador;

    @NotNull
    private String nomeGatilho;

    @NotNull
    private String expressaoCron;

}
