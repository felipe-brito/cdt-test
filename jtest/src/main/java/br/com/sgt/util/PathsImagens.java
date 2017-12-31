package br.com.sgt.util;

import javax.inject.Inject;
import lombok.Data;

/**
 *
 * @author Felipe de Brito Lira
 */
@Data
public class PathsImagens {
    
    @Inject
    private Propriedades sGTProperties;
    
    private final String IMAGEM_BASE = "imagens";
    private final String IMAGEM_SUCESSO = "sucesso.png";
    private final String IMAGEM_ERRO = "error.png";
    
    public String getPathImagemSucesso(){
        return sGTProperties.getSistema().getSeparator() + IMAGEM_BASE + sGTProperties.getSistema().getSeparator() + IMAGEM_SUCESSO;
    } 
    
    public String getPathImagemErro(){
        return sGTProperties.getSistema().getSeparator() + IMAGEM_BASE + sGTProperties.getSistema().getSeparator() + IMAGEM_ERRO;
    } 
    
}
