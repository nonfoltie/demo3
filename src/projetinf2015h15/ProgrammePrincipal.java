/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetinf2015h15;

import java.io.IOException;
import java.util.List;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import static projetinf2015h15.GestionAssurance.*;


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
        traitementReclamations(fichierEntree, fichierSortie);
    }
    
    private static void traitementReclamations(String fichierEntree, String fichierSortie) throws NumberFormatException, IOException {
        JSONObject objet;
        String numClient = "";
        String contrat = "";
        String mois = "";
        Double leRembourssement;
        try {
            objet = formaterObjet(fichierEntree);
            numClient = getNumeroClient(objet);
            contrat = getCategorieContrat(objet);
            mois = getMois(objet);
        } catch (JSONException e) {
            objet = null;
        }
        if (objet != null && validerNumeroClient(numClient) && validerContrat(contrat) && validerFormatMois(mois) && validerLesSoins(objet, mois)) {
            List<JSONObject> listeReclamation = listerLesReclamations(objet);
            for (JSONObject uneReclamation : listeReclamation) {
                int numSoin = uneReclamation.getInt("soin");
                String chaineMontant = uneReclamation.getString("montant");
                int indiceFin = chaineMontant.trim().length();
                Double montant = Double.parseDouble(chaineMontant.substring(0, indiceFin - 1));
                leRembourssement = appliquerLesContrat(contrat, montant, numSoin);
                modifierLeSoin(leRembourssement, uneReclamation);
            }
            String objetJson = creationFichierSortie(numClient, contrat, mois, listeReclamation);
            ecrireFichierSurDisque(fichierSortie, objetJson);
            System.out.println(objetJson);
        } else {
            System.out.println("Données invalides");
        }
    }

}
