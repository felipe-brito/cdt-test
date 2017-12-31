package br.com.sgt.entidades;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Felipe de Brito Lira
 */
@Data
@EqualsAndHashCode(of = {"descricao"})
public class Cabecalho {
    
    private String descricao;
    private Boolean selecionado;
    
    public Cabecalho(){
        selecionado = Boolean.TRUE;
    }
    
    public Cabecalho(String descricao){
        this.selecionado = Boolean.TRUE;
        this.descricao = descricao;
    }
    
}
