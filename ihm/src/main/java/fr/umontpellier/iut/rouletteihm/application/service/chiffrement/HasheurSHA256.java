package fr.umontpellier.iut.rouletteihm.application.service.chiffrement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

// Classe responsable du hachage des données en utilisant l'algorithme SHA-256
public class HasheurSHA256 {

    // Méthode pour hasher les données en utilisant l'algorithme SHA-256
    public String hasherSHA256(String data) {

        try {
            // Initialisation d'une instance de MessageDigest avec l'algorithme SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Obtention du tableau d'octets encodé à partir des données
            byte[] encoded = md.digest(data.getBytes(StandardCharsets.UTF_8));

            // Encodage du tableau d'octets en une chaîne Base64
            String hash = Base64.getEncoder().encodeToString(encoded);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            // Gestion des erreurs liées à l'indisponibilité de l'algorithme de hachage
            throw new RuntimeException(e);
        }

    }

}
