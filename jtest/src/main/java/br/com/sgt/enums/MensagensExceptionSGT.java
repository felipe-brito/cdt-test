package br.com.sgt.enums;

/**
 *
 * @author Felipe de Brito Lira
 */
public enum MensagensExceptionSGT {

    ERRO_LEITURA_ARQUIVO("Não foi possível realizar a leitura do arquivo. Causa: "),
    LOG_TRACE_INVALIDO("Estrutura do arquivo de log não está em conformidade com o padrão esperado, verifique a estrutura do arquivo.");

    private final String erro;
   
    MensagensExceptionSGT(String erro){
        this.erro = erro;
    }
    
    public String getErro(){
        return this.erro;
    }
}
