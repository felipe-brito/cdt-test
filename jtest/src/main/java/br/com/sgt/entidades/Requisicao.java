package br.com.sgt.entidades;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Felipe de Brito Lira
 */
@Data
@EqualsAndHashCode(of = {"chave"})
public class Requisicao {

    private String chave;
    
    private LocalDateTime timestamp;
    
    private String method;
    
    private String path;
    
    Map<String, String> headers;
    
    private Object body;
    
    private Boolean selecionado;
    
    public Requisicao(){
        selecionado = Boolean.TRUE;
    }
    
}
