/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetinf2015h15;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author sergedelil
 */
public class GestionDesOjetsJson {

    /**
     *
     * @author Groupe 15
     * @param objActuel
     * @return la catégorie du contrat.
     */
    public static String getCategorieContrat(JSONObject objActuel) {
        String leContrat = null;
        if (objActuel != null) {
            leContrat = objActuel.getString("contrat");
        }
        return leContrat;
    }

    /**
     *
     * @author Groupe 15
     * @param objActuel
     * @return le numero du client.
     */
    public static String getNumeroClient(JSONObject objActuel) {
        String numeroClient = null;
        if (objActuel != null) {
            numeroClient = objActuel.getString("client");
        }
        return numeroClient;
    }

    /**
     *
     * @author Groupe 15.
     * @param rembourssement
     * @param uneReclamation
     */
    public static void modifierLeSoin(Double rembourssement, JSONObject uneReclamation) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", dfs);
        String leMontant = df.format(rembourssement) + "$";
        uneReclamation.discard("montant");
        uneReclamation.accumulate("montant", leMontant);
    }

    public static String getCategorieDossier(JSONObject objActuel) {
        String leDossier = null;
        if (objActuel != null) {
            leDossier = objActuel.getString("dossier");
        }
        return leDossier;
    }

    /**
     *
     * @author Groupe 15
     * @param objetJson
     * @return la liste des soins
     */
    public static List<JSONObject> listerLesReclamations(JSONObject objetJson) {
        List<JSONObject> listeReclamation = null;
        if (objetJson != null) {
            listeReclamation = new ArrayList<>();
            String reclamation = objetJson.getString("reclamations");
            JSONArray tableauReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
            for (int i = 0; i < tableauReclamation.size(); i++) {
                JSONObject objetCourant = tableauReclamation.getJSONObject(i);
                listeReclamation.add(objetCourant);
            }
        }
        return listeReclamation;
    }

    /**
     *
     * @author Groupe 15
     * @param objActuel
     * @return le mois
     */
    public static String getMois(JSONObject objActuel) {
        String leMois = null;
        if (objActuel != null) {
            leMois = objActuel.getString("mois");
        }
        return leMois;
    }

    
}
