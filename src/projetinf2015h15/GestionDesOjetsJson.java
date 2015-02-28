
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

    public static String creationMessageJson(String message) {
        JSONObject objetJson = new JSONObject();
        objetJson.accumulate("message", message);
        return objetJson.toString();
    }
}
