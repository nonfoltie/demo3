
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * petit commit ....
 * commit 2
 */
package projetinf2015h15;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class GestionDesFichiers {

    
    public static String chargerFichier(String fichierEntree) throws IOException {

        String texteJson = null;

        try {
            texteJson = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
        } catch (FileNotFoundException e) {
            String messageSortie = "Le fichier n'existe pas.";
            gererErreur(messageSortie);
        } 
        return texteJson;
    }

    public static void ecrireFichierSurDisque( String nomFichier, String objet) throws IOException{
        
        try (FileWriter fichierJson = new FileWriter(nomFichier)) {
            fichierJson.write(objet);
            fichierJson.flush();
            fichierJson.close();
        }
    }

    public static String creationFichierSortie(String numClient, String contrat,
            String mois, List<JSONObject> liste) {

        JSONObject objetJson = new JSONObject();

        objetJson.accumulate("client", numClient);
        objetJson.accumulate("contrat", contrat);
        objetJson.accumulate("mois", mois);
        objetJson.accumulate("reclamations", liste);
        
        return objetJson.toString();
    }

    public static JSONObject formaterObjet(String fichierEntree) throws IOException {
        String leTexte = GestionDesFichiers.chargerFichier(fichierEntree);
        JSONObject objActuel = null;
        if (leTexte != null) {
            objActuel = (JSONObject) JSONSerializer.toJSON(leTexte);
        }
        return objActuel;
    }
    
    public static void gererErreur(String message) throws IOException {
        String fichier = "/Users/sergedelil/output.json";
        System.out.println(message);
        ecrireFichierSurDisque(fichier, GestionDesOjetsJson.creationMessageJson(message));
        System.exit(0);
       
    }
}
