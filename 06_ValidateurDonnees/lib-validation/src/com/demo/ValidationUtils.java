package com.demo;

/**
 * Librairie utilitaire pour la validation de donnees.
 * Cette classe fournit des methodes statiques pour valider
 * differents formats de donnees courantes.
 */
public class ValidationUtils {

    /**
     * Valide un email.
     * Un email valide doit contenir un @ et un point apres le @.
     *
     * @param email l'email a valider
     * @return true si l'email est valide, false sinon
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        int atIndex = email.indexOf('@');
        if (atIndex <= 0) {
            return false;
        }
        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex <= atIndex + 1) {
            return false;
        }
        if (dotIndex >= email.length() - 1) {
            return false;
        }
        return true;
    }

    /**
     * Valide un numero de telephone francais.
     * Un telephone valide doit avoir 10 chiffres et commencer par 0.
     * Les espaces et tirets sont ignores.
     *
     * @param telephone le numero a valider
     * @return true si le telephone est valide, false sinon
     */
    public static boolean isValidTelephone(String telephone) {
        if (telephone == null || telephone.isEmpty()) {
            return false;
        }
        // Supprimer espaces et tirets
        String cleaned = telephone.replaceAll("[\\s\\-]", "");

        if (cleaned.length() != 10) {
            return false;
        }
        if (cleaned.charAt(0) != '0') {
            return false;
        }
        for (char c : cleaned.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valide un code postal francais.
     * Un code postal valide doit avoir exactement 5 chiffres.
     *
     * @param codePostal le code postal a valider
     * @return true si le code postal est valide, false sinon
     */
    public static boolean isValidCodePostal(String codePostal) {
        if (codePostal == null || codePostal.isEmpty()) {
            return false;
        }
        if (codePostal.length() != 5) {
            return false;
        }
        for (char c : codePostal.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valide un IBAN francais.
     * Un IBAN francais valide doit :
     * - Commencer par FR
     * - Avoir 27 caracteres au total
     * - Les caracteres 3-4 sont des chiffres (cle de controle)
     * - Les caracteres suivants sont alphanumeriques
     *
     * @param iban l'IBAN a valider
     * @return true si l'IBAN est valide, false sinon
     */
    public static boolean isValidIBAN(String iban) {
        if (iban == null || iban.isEmpty()) {
            return false;
        }
        // Supprimer les espaces
        String cleaned = iban.replaceAll("\\s", "").toUpperCase();

        // Verifier la longueur (FR + 2 cle + 23 = 27 caracteres)
        if (cleaned.length() != 27) {
            return false;
        }
        // Verifier le prefixe FR
        if (!cleaned.startsWith("FR")) {
            return false;
        }
        // Verifier que les caracteres 3 et 4 sont des chiffres (cle)
        if (!Character.isDigit(cleaned.charAt(2)) || !Character.isDigit(cleaned.charAt(3))) {
            return false;
        }
        // Verifier que le reste est alphanumerique
        for (int i = 4; i < cleaned.length(); i++) {
            char c = cleaned.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne un message decrivant le resultat de la validation.
     *
     * @param type le type de donnee validee
     * @param valeur la valeur testee
     * @param valide le resultat de la validation
     * @return un message formaté
     */
    public static String formatResultat(String type, String valeur, boolean valide) {
        if (valide) {
            return type + " '" + valeur + "' : VALIDE";
        } else {
            return type + " '" + valeur + "' : INVALIDE";
        }
    }
}
