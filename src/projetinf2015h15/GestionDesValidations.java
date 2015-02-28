
package projetinf2015h15;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import static projetinf2015h15.GestionDesFichiers.*;

public class GestionDesValidations {

    
    

    public static boolean validerLesSoins(JSONObject objet, String mois) throws IOException {
        int compteurSoins = 0;
            String reclamation = objet.getString("reclamations");
            JSONArray tableauReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
            for (int i = 0; i < tableauReclamation.size(); i++) {
                JSONObject objetCourant = tableauReclamation.getJSONObject(i);
                if (validerNumeroSoin(objetCourant.getInt("soin")) 
                        && validerLaDate(objetCourant.getString("date"), mois) 
                        && validerMontant(objetCourant.getString("montant"))) {
                    compteurSoins++;
                }
            }
           
        
        return compteurSoins == tableauReclamation.size();
    }

    public static boolean validerNumeroDossier(String numero) throws IOException {
        
        boolean reponse = false;
        
        if (numero != null && numero.matches("^[ABCDE]\\d{6}$")) {
            reponse = true;
        }else {
            String messageSortie = "Le numero du dossier n'est pas valide.";
            gererErreur(messageSortie);
        }
        return reponse;
    }

    

    public static boolean validerFormatMois(String mois) throws IOException {
        boolean reponse = false;
        
        if (mois != null && mois.matches("\\d{4}-(0[1-9]|1[0-2])")) {
            reponse = true;
        }else {
            String messageSortie = "Le format du mois n'est pas valide.";
            gererErreur(messageSortie);
        }
        return reponse;
    }
    
    public static boolean validerNumeroSoin(int numeroSoin) throws IOException {
        boolean reponse = false;
        if ((numeroSoin == 0) || (numeroSoin == 100) || (numeroSoin == 200) 
                || (numeroSoin== 400) || (numeroSoin >= 300 && numeroSoin <= 399) 
                || (numeroSoin == 500) || (numeroSoin == 600) || (numeroSoin == 700)
                || (numeroSoin == 150) || (numeroSoin == 175)) {
            reponse = true;
        }else {
            String messageSortie = "Le numero du soin n'est pas valide.";
            gererErreur(messageSortie);
        }
        return reponse;
    }
    
    public static boolean validerLaDate(String date, String mois) throws IOException {
        boolean reponse = identifierLaDateAuMoisEnCours(date, mois);
        if (reponse == false){
                String messageSortie = "La date du soin n'est pas valide.";
                gererErreur(messageSortie);
        }
        return reponse;
    }

    private static boolean identifierLaDateAuMoisEnCours(String date, String mois) throws IOException {
        boolean reponse = false;
        boolean valide;
        String formatDeLaDate = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"; 
        int i = 0;
        if (date != null && date.matches(formatDeLaDate)){
            valide = validerFormatMois(date.trim().substring(0, 7));
            if (valide) {
                while (i < mois.trim().length() && (date.trim().charAt(i) 
                        == mois.trim().charAt(i))) {
                    i++;
                }
                reponse = i == mois.trim().length();
            }
        }
        return reponse;
    }

    public static boolean validerMontant(String montant) throws IOException {
        boolean montantEstValide = false;
        if (montant != null && montant.matches("^\\d+[.,]?\\d{2}\\$$")) {
            montantEstValide = true;
        }else {
            String messageSortie = "Le montant d'un soin est invalide.";
            gererErreur(messageSortie);
        }
        return montantEstValide;
    }

    public static void validerProprietesReclamation(String reclamation) throws IOException {
        JSONArray listeReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
        for (int i = 0; i < listeReclamation.size(); i++) {
            JSONObject objetCourant = listeReclamation.getJSONObject(i);
            verifierNumeroSoin(objetCourant);
            verifierLaDate(objetCourant);
            verifierLeMontant(objetCourant);
        }
    }

    public static void validerLesProprietesJson(JSONObject objet) throws IOException {
        testerDossier(objet);
        testerMois(objet);
        testerReclamation(objet);
    }

    private static void verifierNumeroSoin(JSONObject objetCourant) throws IOException {
        try {
            objetCourant.getInt("soin");
        } catch (JSONException e) {
            String messageSortie = "Le num\u00e9ro d'un soin est manquant.";
            gererErreur(messageSortie);
        }
    }

    private static void verifierLeMontant(JSONObject objetCourant) throws IOException {
        try {
            objetCourant.getString("montant");
        } catch (JSONException e) {
            String messageSortie = "Le montant d'un soin est manquant.";
            gererErreur(messageSortie);
        }
    }

    private static void verifierLaDate(JSONObject objetCourant) throws IOException {
        try {
            objetCourant.getString("date");
        } catch (JSONException e) {
            String messageSortie = "La date d'un soin est manquante.";
            gererErreur(messageSortie);
        }
    }

    private static void testerReclamation(JSONObject objet) throws IOException {
        try {
            objet.getString("reclamations");
        } catch (JSONException e) {
            String messageSortie = "La propri\u00e9t\u00e9 \u00abreclamation\u00bb est manquante.";
            gererErreur(messageSortie);
        }
    }

    private static void testerMois(JSONObject objet) throws IOException {
        try {
            objet.getString("mois");
        } catch (JSONException e) {
            String messageSortie = "La propri\u00e9t\u00e9 \u00abmois\u00bb est manquante.";
            gererErreur(messageSortie);
        }
    }

    private static void testerDossier(JSONObject objet) throws IOException {
        try {
            objet.getString("dossier");
        } catch (JSONException e) {
            String messageSortie = "La propri\u00e9t\u00e9 \u00abdossier\u00bb est manquante.";
            gererErreur(messageSortie);
        }
    }

}
