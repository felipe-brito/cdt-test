package br.com.sgt.entidades;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Felipe de Brito Lira
 */
@Data
@EqualsAndHashCode(of = {"header"})
public class Headers {
    
    private String header;
    private Boolean selecionado;
    
    public Headers(){
        selecionado = Boolean.TRUE;
    }
    
    public Headers(String header){
        this.selecionado = Boolean.TRUE;
        this.header = header;
    }
    
}
