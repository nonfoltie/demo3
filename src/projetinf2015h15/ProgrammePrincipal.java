/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetinf2015h15;

import java.io.IOException;

/**
 *
 * @author Groupe 15
 */
public class ProgrammePrincipal {
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        if(args.length != 2){
            System.out.println("Données invalides");
            System.exit(0);
        }
        
        String fichierEntree = args[0];
        String fichierSortie = args[1];
        
        GestionAssurance.traitementReclamations(fichierEntree, fichierSortie);
    }

}
