/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author Saumya
 */
public class EncryptionUtil {

    private static final String SECRET_KEY = "YourSecretKey";

    public static String encrypt(String strToEncrypt) {
        try {
            return Base64.getEncoder().encodeToString(strToEncrypt.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            return strToEncrypt;
        }
    }

    public static String decrypt(String strToDecrypt) {
        try {
            // if not Base64, just return original
            if (!isBase64(strToDecrypt)) {
                return strToDecrypt;
            }
            byte[] decoded = Base64.getDecoder().decode(strToDecrypt);
            return new String(decoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return strToDecrypt; // fallback to original
        }
    }

    private static boolean isBase64(String value) {
        try {
            Base64.getDecoder().decode(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
