
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Groupe 15
 */
public class GestionDesFichiers {

    /**
     *
     * @author Groupe 15 Changement du fichier d'entrée.
     * @param fichierEntree
     * @return
     * @throws java.io.IOException
     */
    public static String chargerFichier(String fichierEntree) throws IOException {

        String texteJson;

        try {
            texteJson = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
        } catch (FileNotFoundException e) {
            texteJson = null;
        } catch (Exception e) {
            texteJson = null;
        }
        return texteJson;
    }

    
    /**
     *
     * @author Groupe 15.
     * @param nomFichier
     * @param objet
     * @throws java.io.IOException
     */
    public static void ecrireFichierSurDisque( String nomFichier, String objet) throws IOException{
        
        try (FileWriter fichierJson = new FileWriter(nomFichier)) {
            fichierJson.write(objet);
            fichierJson.flush();
            fichierJson.close();
        }
    }


    /**
     *
     * @author Groupe 15.
     * @param numClient
     * @param contrat
     * @param mois
     * @param liste
     * @return
     */
    public static String creationFichierSortie(String numClient, String contrat,
            String mois, List<JSONObject> liste) {

        JSONObject objetJson = new JSONObject();

        objetJson.accumulate("client", numClient);
        objetJson.accumulate("contrat", contrat);
        objetJson.accumulate("mois", mois);
        objetJson.accumulate("reclamations", liste);
        
        return objetJson.toString();
    }

    /**
     *
     * @author Groupe 15.
     * @param fichierEntree
     * @return
     * @throws java.io.IOException
     */
    public static JSONObject formaterObjet(String fichierEntree) throws IOException {
        String leTexte = GestionDesFichiers.chargerFichier(fichierEntree);
        JSONObject objActuel = null;
        if (leTexte != null) {
            objActuel = (JSONObject) JSONSerializer.toJSON(leTexte);
        }
        return objActuel;
    }

    
    }
