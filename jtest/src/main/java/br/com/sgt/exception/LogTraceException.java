/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgt.exception;

/**
 *
 * @author Felipe de Brito Lira
 */
public class LogTraceException extends Exception{

    public LogTraceException() {
    }

    public LogTraceException(String message) {
        super(message);
    }

    public LogTraceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogTraceException(Throwable cause) {
        super(cause);
    }
    
}
