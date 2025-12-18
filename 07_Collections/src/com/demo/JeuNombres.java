package com.demo;

import java.util.HashSet;
import java.util.Random;

/**
 * Exercice 2 : Generer 5 nombres aleatoires differents
 * Utilisation de HashSet pour garantir l'unicite des elements
 */
public class JeuNombres {
    public static void main(String[] args) {
        HashSet<Integer> nombres = new HashSet<>();
        Random random = new Random();

        System.out.println("Generation de 5 nombres uniques entre 0 et 10...");

        int tentative = 0;

        // Boucle jusqu'a avoir 5 nombres uniques
        while (nombres.size() < 5) {
            tentative++;
            int nombre = random.nextInt(11); // 0 a 10 inclus

            // add() retourne true si l'element est ajoute, false s'il existe deja
            boolean ajoute = nombres.add(nombre);

            if (ajoute) {
                System.out.println("Tentative " + tentative + " : " + nombre + " (ajoute)");
            } else {
                System.out.println("Tentative " + tentative + " : " + nombre + " (deja present)");
            }
        }

        System.out.println();
        System.out.println("Les 5 nombres generes : " + nombres);
    }
}
