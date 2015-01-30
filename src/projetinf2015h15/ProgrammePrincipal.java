/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetinf2015h15;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import net.sf.json.JSONObject;
import static projetinf2015h15.GestionAssurance.*;
/*import static projetinf2015h15.GestionAssurance.appliquerLesContrat;
import static projetinf2015h15.GestionAssurance.creationFichierSortie;
import static projetinf2015h15.GestionAssurance.formaterObjet;
import static projetinf2015h15.GestionAssurance.getCategorieContrat;
import static projetinf2015h15.GestionAssurance.getMois;
import static projetinf2015h15.GestionAssurance.getNumeroClient;
import static projetinf2015h15.GestionAssurance.listerLesReclamations;
import static projetinf2015h15.GestionAssurance.modifierLeSoin;
import static projetinf2015h15.GestionAssurance.validerContrat;
import static projetinf2015h15.GestionAssurance.validerFormatMois;
import static projetinf2015h15.GestionAssurance.validerLesSoins;
import static projetinf2015h15.GestionAssurance.validerNumeroClient;*/

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
        
        JSONObject objet = formaterObjet(fichierEntree);
        String numClient = getNumeroClient(objet);
        String contrat = getCategorieContrat(objet);
        String mois = getMois(objet);
        Double leRembourssement;
        
        if (objet != null && validerNumeroClient(numClient)
                && validerContrat(contrat) && validerFormatMois(mois)
                && validerLesSoins(objet, mois)){

            List<JSONObject> listeReclamation = listerLesReclamations(objet);

            for (JSONObject uneReclamation : listeReclamation) {
                int numSoin = uneReclamation.getInt("soin");
                
                String chaineMontant = uneReclamation.getString("montant");
                int indiceFin = chaineMontant.trim().length();
                Double montant = Double.parseDouble(chaineMontant.substring(0, indiceFin - 1));
                
                leRembourssement = appliquerLesContrat(contrat, montant, numSoin);
                modifierLeSoin(leRembourssement, uneReclamation);

            } 
            
            String objetJson = creationFichierSortie(numClient, contrat, mois,listeReclamation);
            
            try {
                 
                FileWriter fichierJson = new FileWriter("c:\\test.json");
                fichierJson.write(objetJson);
                fichierJson.flush();
                fichierJson.close();
 
            } catch (IOException e) {
                e.getMessage();
            }
             
            System.out.println(objetJson);
            
        }else {
            
            System.out.println("Données invalides");
        }
    }
}
