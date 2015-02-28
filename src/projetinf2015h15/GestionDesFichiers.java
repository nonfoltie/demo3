
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
import static projetinf2015h15.GestionDesOjetsJson.*;


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

    public static String creationFichierSortie(JSONObject objet, 
            List<JSONObject> liste, String total) throws IOException {

        JSONObject objetJson = new JSONObject();
        objetJson.accumulate("dossier", getNumeroDossier(objet));
        objetJson.accumulate("mois", getMois(objet));
        objetJson.accumulate("reclamations", liste);
        objetJson.accumulate("total", total);
        
        return objetJson.toString();
    }

    public static void gererErreur(String message) throws IOException {
        String fichier = "resources/output.json";
        System.out.println(message);
        ecrireFichierSurDisque(fichier, GestionDesOjetsJson.creationMessageJson(message));
        System.exit(0);
       
    }
}
