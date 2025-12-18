package com.demo;

import java.util.Scanner;

/**
 * Application de validation de donnees.
 * Utilise la librairie ValidationUtils pour valider
 * des emails, telephones, codes postaux et IBAN.
 */
public class Validateur {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== VALIDATEUR DE DONNEES ===");
        System.out.println("Application utilisant la librairie validation-utils.jar\n");

        boolean continuer = true;

        while (continuer) {
            afficherMenu();
            int choix = lireChoix();

            switch (choix) {
                case 1:
                    validerEmail();
                    break;
                case 2:
                    validerTelephone();
                    break;
                case 3:
                    validerCodePostal();
                    break;
                case 4:
                    validerIBAN();
                    break;
                case 5:
                    validerTout();
                    break;
                case 6:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.\n");
            }
        }

        System.out.println("\nMerci d'avoir utilise le Validateur de donnees !");
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Valider un email");
        System.out.println("2. Valider un telephone");
        System.out.println("3. Valider un code postal");
        System.out.println("4. Valider un IBAN");
        System.out.println("5. Valider un contact complet");
        System.out.println("6. Quitter");
        System.out.print("\nVotre choix : ");
    }

    private static int lireChoix() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void validerEmail() {
        System.out.print("\nEntrez l'email a valider : ");
        String email = scanner.nextLine();
        boolean valide = ValidationUtils.isValidEmail(email);
        System.out.println(ValidationUtils.formatResultat("Email", email, valide));
        System.out.println();
    }

    private static void validerTelephone() {
        System.out.print("\nEntrez le telephone a valider : ");
        String telephone = scanner.nextLine();
        boolean valide = ValidationUtils.isValidTelephone(telephone);
        System.out.println(ValidationUtils.formatResultat("Telephone", telephone, valide));
        System.out.println();
    }

    private static void validerCodePostal() {
        System.out.print("\nEntrez le code postal a valider : ");
        String codePostal = scanner.nextLine();
        boolean valide = ValidationUtils.isValidCodePostal(codePostal);
        System.out.println(ValidationUtils.formatResultat("Code postal", codePostal, valide));
        System.out.println();
    }

    private static void validerIBAN() {
        System.out.print("\nEntrez l'IBAN a valider : ");
        String iban = scanner.nextLine();
        boolean valide = ValidationUtils.isValidIBAN(iban);
        System.out.println(ValidationUtils.formatResultat("IBAN", iban, valide));
        System.out.println();
    }

    private static void validerTout() {
        System.out.println("\n=== VALIDATION CONTACT COMPLET ===");

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Telephone : ");
        String telephone = scanner.nextLine();

        System.out.print("Code postal : ");
        String codePostal = scanner.nextLine();

        System.out.print("IBAN : ");
        String iban = scanner.nextLine();

        System.out.println("\n--- RESULTATS ---");

        boolean emailOk = ValidationUtils.isValidEmail(email);
        boolean telOk = ValidationUtils.isValidTelephone(telephone);
        boolean cpOk = ValidationUtils.isValidCodePostal(codePostal);
        boolean ibanOk = ValidationUtils.isValidIBAN(iban);

        System.out.println(ValidationUtils.formatResultat("Email", email, emailOk));
        System.out.println(ValidationUtils.formatResultat("Telephone", telephone, telOk));
        System.out.println(ValidationUtils.formatResultat("Code postal", codePostal, cpOk));
        System.out.println(ValidationUtils.formatResultat("IBAN", iban, ibanOk));

        int nbValides = 0;
        if (emailOk) nbValides++;
        if (telOk) nbValides++;
        if (cpOk) nbValides++;
        if (ibanOk) nbValides++;

        System.out.println("\nBilan : " + nbValides + "/4 champs valides");

        if (nbValides == 4) {
            System.out.println("Contact VALIDE !");
        } else {
            System.out.println("Contact INCOMPLET - veuillez corriger les erreurs.");
        }
        System.out.println();
    }
}
