package br.com.sgt.util;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Felipe de Brito Lira
 */
@Data
@AllArgsConstructor
public class Propriedades implements Serializable{
    
    private Sistema sistema;
    private Json json;
    
    @Data
    @NoArgsConstructor
    public class Sistema implements Serializable{
        
        private String separator;
        
    }
    
    @Data
    @NoArgsConstructor
    public class Json implements Serializable{
        
        private String startWith;
        
    }
    
}
