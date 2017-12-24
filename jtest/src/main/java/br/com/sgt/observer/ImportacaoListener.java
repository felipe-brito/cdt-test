package br.com.sgt.observer;

/**
 *
 * @author Felipe de Brito Lira
 */
public interface ImportacaoListener {
    
    /**
     * Atualiza o ouvinte registra em alguma lista de observadores
     * 
     * @param object 
     */
    public void update(Object object);
    
}
