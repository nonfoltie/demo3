
package projetinf2015h15;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class GestionDesValidations {

    
    public static boolean validerNumeroSoin(int numSoin) {
        boolean reponse = false;
        if ((numSoin == 0) || (numSoin == 100) || (numSoin == 200) || (numSoin == 400) || (numSoin >= 300 && numSoin <= 399) || (numSoin == 500) || (numSoin == 600) || (numSoin == 700)) {
            reponse = true;
        }
        return reponse;
    }

    public static boolean validerLesSoins(JSONObject objet, String mois) {
        boolean soinsValide = false;
        int compteurSoins = 0;
        if (objet != null && mois != null) {
            String reclamation = objet.getString("reclamations");
            JSONArray tableauReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
            for (int i = 0; i < tableauReclamation.size(); i++) {
                JSONObject objetCourant = tableauReclamation.getJSONObject(i);
                if (validerLaDate(objetCourant.getString("date"), mois) && validerMontant(objetCourant.getString("montant")) && validerNumeroSoin(objetCourant.getInt("soin"))) {
                    compteurSoins++;
                }
            }
            soinsValide = compteurSoins == tableauReclamation.size();
        }
        return soinsValide;
    }

    public static boolean validerContrat(String contrat) {
        boolean reponse = false;
        if (contrat != null && (contrat.equals("A") || contrat.equals("B") || contrat.equals("C") || contrat.equals("D"))) {
            reponse = true;
        }
        return reponse;
    }

    public static boolean validerNumeroClient(String numero) {
        boolean reponse = false;
        int i = 0;
        int j;
        if (numero != null && numero.trim().length() == 6) {
            j = numero.trim().length();
            while (i < j && (numero.trim().charAt(i) >= '0' && numero.trim().charAt(i) <= '9')) {
                i++;
            }
            reponse = i == j;
        }
        return reponse;
    }

    public static boolean validerFormatMois(String mois) {
        boolean reponse = false;
        final int ANNEE_MAX = 2015;
        final int ANNEE_MIN = 1;
        final int MOIS_MIN = 1;
        final int MOIS_MAX = 12;
        int i = 0;
        int j;
        if (mois != null && (mois.trim().length() == 7) && Integer.parseInt(mois.substring(0, 4)) >= ANNEE_MIN && Integer.parseInt(mois.substring(0, 4)) <= ANNEE_MAX && Integer.parseInt(mois.substring(5, 7)) >= MOIS_MIN && Integer.parseInt(mois.substring(5, 7)) <= MOIS_MAX) {
            j = mois.trim().length();
            while (i < 4 && (mois.charAt(i) >= '0' && mois.charAt(i) <= '9')) {
                i++;
            }
            if (mois.charAt(i) == '-') {
                i++;
                while (i < 7 && (mois.charAt(i) >= '0' && mois.charAt(i) <= '9')) {
                    i++;
                }
            }
            reponse = i == j;
        }
        return reponse;
    }

    public static boolean validerMontant(String montant) {
        boolean montantEstValide = false;
        if (montant != null && montant.trim().charAt(montant.trim().length() - 1) == '$') {
            try {
                Double.parseDouble(montant.trim().substring(0, montant.trim().length() - 1));
                montantEstValide = true;
            } catch (NumberFormatException e) {
                montantEstValide = false;
            }
        }
        return montantEstValide;
    }

    public static boolean validerLaDate(String date, String mois) {
        boolean reponse = false;
        boolean valide;
        int i = 0;
        if (date != null && date.trim().length() >= 7) {
            valide = validerFormatMois(date.trim().substring(0, 7));
            if (valide) {
                while (i < mois.trim().length() && (date.trim().charAt(i) == mois.trim().charAt(i))) {
                    i++;
                }
                reponse = i == mois.trim().length();
            }
        }
        return reponse;
    }
    
}
