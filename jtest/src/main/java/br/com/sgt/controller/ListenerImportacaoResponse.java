package br.com.sgt.controller;

import br.com.sgt.enums.StatusBarraProgresso;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Felipe de Brito Lira
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListenerImportacaoResponse implements Serializable{
        
    private Integer totalImportacoes;
    
    private Integer valorImportacoesSucesso;
    
    private Integer valorImportacoesErro;
    
    private Integer valorProgresso;
    
    private StatusBarraProgresso statusBarraProgresso;
    
}
