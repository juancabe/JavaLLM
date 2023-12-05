package model;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class SHA1Calculator {

    public static String calculateSHA1(String filePath) throws Exception{
        
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        // Lee el archivo en bloques de 4 KB para evitar cargar el archivo completo en memoria
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        // Convierte el hash a una representación hexadecimal
        byte[] hashBytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
 