package io;

import java.util.ArrayList;
import java.util.Scanner;

import uno.Carte;

/**
 * cette classe nous permet d'interagir avec le joueur a travers le clavier
 * @author louispuyo
 *
 */
public class Clavier {

    /**
     * grace e cet objet on peut lire e partir du clavier
     */
    private static Scanner scanner = new Scanner( System.in );

    /**
     * permet de lire un entier e partir du clavier
     * @return l'entier lu
     */
    public static int lireEntier() {
        System.out.print( "? >> " );
        int res = scanner.nextInt();
        scanner.nextLine(); 
        
        return res;
    }

    /**
     * permet de lire un entier entre min et max
     * @param min	la borne inferieure de l'entier e lire
     * @param max	la borne superieure de l'entier e lire
     * @return		l'entier qu'on veut lire du clavier
     */
    public static int lireEntier( int min, int max ) {
        if ( min > max ) {
            // inverser les bornes
            int temp = min;
            min = max;
            max = temp;
        }
        boolean horsBornes;
        int num;
        do {
            num = lireEntier();
            horsBornes = num < min || num > max;
            if ( horsBornes ) {
                System.out.println( "indice hors bornes ! [" + min + ".." + max + "]" );
            }
        } while ( horsBornes );
        return num;
    }

    /**
     * permet de lire un entier qui represente l'une des cases de la liste arrayList
     * @param arrayList la liste dont on veut lire un indice valide
     * @return l'indice de l'une des cases de la liste arrayList
     */
    public static int lireEntier( ArrayList<Carte> arrayList ) {
        if ( arrayList.isEmpty() ) {
            return -1;
        }
        return lireEntier( 0, arrayList.size() - 1 ); // les listes sont indexes de 0 e nbElements - 1
    }

    /**
     * permet de lire une chaene de caracteres e partir du clavier
     * @return la chaene lue
     */
    public static String lireChaine() {
        System.out.print( "? >> " );
        return scanner.nextLine();
    }

    /**
     * permer de fermer la variable scanner e la fin du programme
     */
    public static void fermer() {
        scanner.close();
    }

}
