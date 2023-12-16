package fr.umontpellier.iut.rouletteihm.application.service.chiffrement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HasheurSHA256 {

    public String hasherSHA256(String data) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encoded = md.digest(data.getBytes(StandardCharsets.UTF_8));
            String hash = Base64.getEncoder().encodeToString(encoded);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
