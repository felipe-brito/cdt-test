package br.com.sgt.enums;

/**
 *
 * @author Felipe de Brito Lira
 */
public enum StatusBarraProgresso {
    
    START(0), STOP(100); 
    
    private final Integer valor;
    
    StatusBarraProgresso(Integer valor){
        this.valor = valor;
    }
    
    public Integer getValor(){
        return this.valor;
    }
    
}
