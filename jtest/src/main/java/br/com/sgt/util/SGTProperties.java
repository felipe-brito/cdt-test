package br.com.sgt.util;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Felipe de Brito Lira
 */
@Data
@AllArgsConstructor
public class SGTProperties implements Serializable{
    
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
        private List<String> pathsExclused;
        
    }
    
}
