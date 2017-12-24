package br.com.sgt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 *
 * @author Felipe de Brito Lira
 */
public class Random {

    /**
     * Método gerador de uma String única aleatória.
     * @return 
     */
    public static String getString() {

        String codigo = UUID.randomUUID().toString();
        String hash;

        try {

            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte[] message = algorithm.digest(codigo.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder();

            for (byte b : message) {
                hex.append(String.format("%02X", 0xFF & b));
            }

            hash = hex.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return "";
        }
        return hash;
    }
    
}
