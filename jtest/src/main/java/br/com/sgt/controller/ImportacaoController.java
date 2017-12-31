package br.com.sgt.controller;

import br.com.sgt.observer.ImportacaoListener;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import lombok.Data;

/**
 *
 * @author Felipe de Brito Lira
 */
@Singleton
@Data
public class ImportacaoController {
    
    private List<ImportacaoListener> importacaoListener;
    private Boolean pauseImportacao;
    private Boolean cancelarImportacao;
    private Boolean confirmarImportacao;
    
    @PostConstruct
    public void init(){
        this.importacaoListener = Lists.newArrayList();
        zerarControlesImportacao();
    }
    
    /**
     * Adiciona um ouvinte a lista de importação
     * 
     * @param importacaoListener 
     */
    public void addImportacaoObserver(ImportacaoListener importacaoListener){
        this.importacaoListener.add(importacaoListener);
    }
    
    /**
     * Remove um ouvinte da importação da lista
     * 
     * @param importacaoListener 
     */
    public void removerImportacaoObserver(ImportacaoListener importacaoListener){
        this.importacaoListener.remove(importacaoListener);
    }
    
    /**
     * Notifica os ouvintes para os eventos de importação
     * 
     * @param object 
     */
    public void notificarImportacaoObeserver(Object object){
        this.importacaoListener.forEach((subject) -> {
            subject.update(object);
        });
    }
    
    public void controlePauseImportacao(Boolean bool){
        this.pauseImportacao = bool;
    }
    
    public void controleCancelarImportacao(Boolean bool){
        this.cancelarImportacao = bool;
    }
    
    public void controleConfirmarImportacao(Boolean bool){
        this.confirmarImportacao = bool;
    }
    
    public void zerarControlesImportacao(){
        this.pauseImportacao = Boolean.FALSE;
        this.cancelarImportacao = Boolean.FALSE;
        this.confirmarImportacao = Boolean.TRUE;
    }
    
    public void clean(){
        if(this.importacaoListener != null){
            this.importacaoListener.clear();
        }
    }
    
}
