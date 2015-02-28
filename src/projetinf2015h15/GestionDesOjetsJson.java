
package projetinf2015h15;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import static projetinf2015h15.GestionDesFichiers.*;

public class GestionDesOjetsJson {
    
    public static JSONObject formaterObjet(String texteJson) throws JSONException, IOException {
        JSONObject objActuel = null;
        if (texteJson.trim().length() != 0) {
            try{
                objActuel = (JSONObject) JSONSerializer.toJSON(texteJson);
            }catch (JSONException e){
                String messageSortie = "Le fichier JSON n'est pas valide.";
                gererErreur(messageSortie);
            }
        }else {
            String messageSortie = "Le fichier est vide.";
            gererErreur(messageSortie);
        }
        return objActuel;
    }
    public static void validerLesProprietesJson(JSONObject objet) throws IOException {
        testerDossier(objet);
        testerMois(objet);
        testerReclamation(objet);
    }

    private static void testerReclamation(JSONObject objet) throws IOException {
        try{
            objet.getString("reclamations");
        }catch (JSONException e){
            String messageSortie = "La propriété «reclamation» est manquante.";
            gererErreur(messageSortie);
        }
    }

    private static void testerMois(JSONObject objet) throws IOException {
        try{
            objet.getString("mois");
        }catch (JSONException e){
            String messageSortie = "La propriété «mois» est manquante.";
            gererErreur(messageSortie);
        }
    }

    private static void testerDossier(JSONObject objet) throws IOException {
        try{
            
            objet.getString("dossier");
        }catch (JSONException e){
            String messageSortie = "La propriété «dossier» est manquante.";
            gererErreur(messageSortie);
        }
    }
    public static void validerProprietesReclamation(String reclamation) 
            throws IOException {
        
        JSONArray listeReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
        for (int i = 0; i < listeReclamation.size(); i++) {
            JSONObject objetCourant = listeReclamation.getJSONObject(i);
            verifierNumeroSoin(objetCourant);
            verifierLaDate(objetCourant);
            verifierLeMontant(objetCourant);
        }
    }

    private static void verifierLeMontant(JSONObject objetCourant) throws IOException {
        try{
            objetCourant.getString("montant");
        }catch (JSONException e){
            String messageSortie = "Le montant d'un soin est manquant.";
            gererErreur(messageSortie);
        }
    }

    private static void verifierLaDate(JSONObject objetCourant) throws IOException {
        try{
            objetCourant.getString("date");
        }catch (JSONException e){
            String messageSortie = "La date d'un soin est manquante.";
            gererErreur(messageSortie);
        }
    }

    private static void verifierNumeroSoin(JSONObject objetCourant) throws IOException {
        try{
            objetCourant.getInt("soin");
        }catch (JSONException e){
            String messageSortie = "Le numéro d'un soin est manquant.";
            gererErreur(messageSortie);
        }
    }
    public static String getNumeroDossier(JSONObject objActuel) throws IOException {
        return objActuel.getString("dossier");
    }
    
    public static String getMois(JSONObject objActuel) throws IOException {
        return objActuel.getString("mois");
    }

    public static void modifierLeSoin(Double remboursement, JSONObject uneReclamation) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", dfs);
        String leMontant = df.format(remboursement) + "$";
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

    public static String getMois(JSONObject objActuel) {
        String leMois = null;
        if (objActuel != null) {
            leMois = objActuel.getString("mois");
        }
        return leMois;
    }

    public static String creationMessageJson(String message) {
        JSONObject objetJson = new JSONObject();
        objetJson.accumulate("message", message);
        return objetJson.toString();
    }
}
