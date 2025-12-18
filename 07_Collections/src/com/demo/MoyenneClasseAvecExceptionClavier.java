package com.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class MoyenneClasseAvecExceptionClavier {

    public static void main(String[] args) {

        ArrayList<Integer> notes = new ArrayList<>();
        Scanner clavier = new Scanner(System.in);
        int valeurClavier = 0;

        do {
            System.out.println("saisir une note sur 20");
            System.out.println("ou -1 pour quitter");
            try {
                valeurClavier = clavier.nextInt();
            } catch (Exception e) {
                valeurClavier = -1;
                System.out.println("attention seuls des nombres sont acceptés");
            }
            if (valeurClavier != -1 && valeurClavier >= 0 && valeurClavier <= 20)
                notes.add(valeurClavier);

        } while (valeurClavier != -1);

        int total = 0;
        for (Integer i : notes) {
            total += i;
        }
        System.out.println("moyenne : " + total / notes.size());
    }
}
