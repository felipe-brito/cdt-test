/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgt.enums;

/**
 *
 * @author Felipe de Brito Lira
 */
public enum TipoArquivoImportacao {
    
    TXT(".txt","txt");
    
    private final String descricao;
    private final String extensao;
    
    TipoArquivoImportacao(String descricao, String extensao){
        this.descricao = descricao;
        this.extensao = extensao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getExtensao() {
        return extensao;
    }
    
}
