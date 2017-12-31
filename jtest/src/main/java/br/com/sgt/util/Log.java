package br.com.sgt.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Felipe de Brito Lira
 */
public class Log {

    private static final Logger LOGGER = LogManager.getLogger(Log.class);
    
    public static void info(String msg){
        LOGGER.info(msg);
    }
    
    public static void error(String msg){
        LOGGER.error(msg);
    }
    
}
