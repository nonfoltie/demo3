
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
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Groupe 15
 */
public class GestionAssurance {

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
     * @param fichierEntree
     * @return
     * @throws java.io.IOException
     */
    public static JSONObject formaterObjet(String fichierEntree) throws IOException {

        String leTexte = chargerFichier(fichierEntree);
        JSONObject objActuel = null;

        if (leTexte != null) {

            objActuel = (JSONObject) JSONSerializer.toJSON(leTexte);
        }
        return objActuel;

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
     * @return le mois
     */
    public static String getMois(JSONObject objActuel) {
        String leMois = null;

        if (objActuel != null) {
            leMois = objActuel.getString("mois");
        }
        return leMois;
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
     * @author Groupe 15
     * @param numero
     * @return vrai si le numéro du client est valide. Sinon, faux.
     */
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

    /**
     * @author Groupe 15.
     * @param contrat
     * @return vrai si le contrat est valide. Sinon, faux.
     */
    public static boolean validerContrat(String contrat) {

        boolean reponse = false;

        if (contrat != null && (contrat.equals("A") || contrat.equals("B")
                || contrat.equals("C") || contrat.equals("D"))) {

            reponse = true;
        }

        return reponse;
    }

    /**
     * @author Groupe 15
     * @param mois
     * @return vrai si le format du mois est valide. Sinon, faux.
     */
    public static boolean validerFormatMois(String mois) {

        boolean reponse = false;
        final int ANNEE_MAX = 2015;
        final int ANNEE_MIN = 1;
        final int MOIS_MIN = 1;
        final int MOIS_MAX = 12;
        int i = 0;
        int j;

        if (mois != null && (mois.trim().length() == 7)
                && Integer.parseInt(mois.substring(0, 4)) >= ANNEE_MIN
                && Integer.parseInt(mois.substring(0, 4))<= ANNEE_MAX
                && Integer.parseInt(mois.substring(5, 7)) >= MOIS_MIN
                && Integer.parseInt(mois.substring(5, 7)) <= MOIS_MAX) {

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
    /**
     *
     * @author sergedelil
     * 
     * @param numSoin
     * @return vrai si le numéro du soin est valide. Sinon, faux.
    */
     public static boolean validerNumeroSoin(int numSoin){
         
         boolean reponse = false;
        
       if((numSoin == 0) || (numSoin == 100) || (numSoin == 200) || (numSoin == 400)
               || (numSoin >= 300 && numSoin <= 399) || (numSoin == 500)
               || (numSoin == 600) || (numSoin == 700)){
          
           reponse = true;
       }
       
       return reponse;
    } 
    /**
     *
     * @author Groupe 15.
     * @param date
     * @param mois
     * @return retourne vrai si la date est valide, sinon, faux.
     */
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

    /**
     *
     * @author Groupe 15.
     * @param montant
     * @return vrai si le montant est valide. Sinon, faux.
     */
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

    /**
     *
     * @author Groupe 15.
     * @param objet
     * @param mois
     * @return vrai si les soins sont valides. Sinon, faux.
     */
public static boolean validerLesSoins(JSONObject objet, String mois) {

        boolean soinsValide = false;
        int compteurSoins = 0;

        if (objet != null && mois != null){

            String reclamation = objet.getString("reclamations");
            JSONArray tableauReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);

            for(int i = 0; i < tableauReclamation.size(); i++ ){

                JSONObject objetCourant = tableauReclamation.getJSONObject(i);

                if(validerLaDate(objetCourant.getString("date"), mois)
                        && validerMontant(objetCourant.getString("montant"))
                        && validerNumeroSoin(objetCourant.getInt("soin"))){

                    compteurSoins++;
                }
            }
            soinsValide = compteurSoins == tableauReclamation.size();
        }
        return soinsValide;
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
        DecimalFormat df = new DecimalFormat("0.00" , dfs); 
        
        String leMontant = df.format(rembourssement) + "$";
        uneReclamation.discard("montant");
        uneReclamation.accumulate("montant", leMontant);

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
     * @param contrat
     * @param montant
     * @param numSoin
     * @return le montant du rembourcement
     */
    public static Double appliquerLesContrat(String contrat, Double montant, int numSoin) {
        
        
        
        Double rembourssement = 0.0;
        final double TAUX_25_POUR_CENT = 0.25;
        final double TAUX_40_POUR_CENT = 0.40;
        final double TAUX_50_POUR_CENT = 0.50;
        final double TAUX_70_POUR_CENT = 0.70;
        final double TAUX_90_POUR_CENT = 0.90;

        switch (contrat) {
        
            case "A":
                if (numSoin == 0 || numSoin == 100 || numSoin == 200 || numSoin == 500) {
                    rembourssement = montant * TAUX_25_POUR_CENT;

                } else if ((numSoin >= 300 && numSoin <= 399) || (numSoin == 400)
                        || (numSoin == 700)) {
                    rembourssement = 0.00;

                } else {
                    rembourssement = montant * TAUX_40_POUR_CENT;
                }

                break;

            case "B":

                if (numSoin == 100 || numSoin == 500) {
                    rembourssement = montant * TAUX_50_POUR_CENT;

                    if (rembourssement > 50) {

                        rembourssement = 50.00;
                    }
                }else if (numSoin == 0) {
                    rembourssement = montant * TAUX_50_POUR_CENT;

                    if (rembourssement > 50) {

                        rembourssement = 40.00;
                    }

                } else if ((numSoin >= 300 && numSoin <= 399)) {

                    rembourssement = montant * TAUX_50_POUR_CENT;

                
                } else if (numSoin == 200) {

                    rembourssement = montant;

                    if (rembourssement > 70) {

                        rembourssement = 70.00;
                    }

                } else if (numSoin == 400) {

                    rembourssement = 0.00;

                } else if (numSoin == 600) {

                    rembourssement = montant;

                } else {

                    rembourssement = montant * TAUX_70_POUR_CENT;
                }

                break;

            case "C":

                rembourssement = montant * TAUX_90_POUR_CENT;

                break;

            case "D":

                if (numSoin == 200 || numSoin == 600) {

                    rembourssement = montant;

                    if (rembourssement > 100) {

                        rembourssement = 100.00;
                    }

                } else if ((numSoin >= 300 && numSoin <= 399)) {

                    rembourssement = montant;

                } else if (numSoin == 100 || numSoin == 500) {

                    rembourssement = montant;

                    if (rembourssement > 75) {

                        rembourssement = 75.00;
                    }
                } else if (numSoin == 0) {

                    rembourssement = montant;

                    if (rembourssement > 85) {

                        rembourssement = 85.00;
                    }

                } else if (numSoin == 400) {

                    rembourssement = montant;

                    if (rembourssement > 65) {

                        rembourssement = 65.00;
                    }

                } else if (numSoin == 700) {

                    rembourssement = montant;

                    if (rembourssement > 90) {

                        rembourssement = 90.00;
                    }

                }
                break;
        }
        return rembourssement;
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

    public static void traitementReclamations(String fichierEntree, String fichierSortie) throws NumberFormatException, IOException {
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
