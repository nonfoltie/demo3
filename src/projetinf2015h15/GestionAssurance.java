/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetinf2015h15;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
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
     * @return
     * @throws java.io.IOException
     */
    public static String chargerFichier() throws IOException {

        String texteJson = null;

        try {
            texteJson = FileReader.loadFileIntoString("resources/inputFile.json", "UTF-8");
        } catch (FileNotFoundException e) {
            return texteJson;
        } catch (Exception e) {
            return texteJson;
        }

        return texteJson;
    }

    /**
     *
     * @author Groupe 15 Création de l'objet.
     * @return
     * @throws java.io.IOException
     */
    public static JSONObject formaterObjet() throws IOException {

        String leTexte = chargerFichier();
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
     * @author Groupe 15 retourne la liste des dates des soins.
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
        int i = 0;
        int j;

        if (mois != null && (mois.trim().length() == 7)
                && Integer.parseInt(mois.substring(0, 4)) >= 1
                && Integer.parseInt(mois.substring(5, 7)) >= 1
                && Integer.parseInt(mois.substring(5, 7)) <= 12) {

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
     * @author Groupe 15 retourne la liste des dates des soins.
     * @param date
     * @param mois
     * @return
     */
    public static boolean validerLaDate(String date, String mois) {

        boolean reponse = false;
        boolean valide;
        int i = 0;

        if (date != null && date.length() >= 7) {

            valide = validerFormatMois(date.trim().substring(0, 7));

            if (valide) {

                while (i < mois.length() && (date.charAt(i) == mois.charAt(i))) {
                    i++;
                }
                reponse = i == mois.length();
            }
        }
        return reponse;
    }

    /**
     *
     * @author Groupe 15 retourne la liste des dates des soins.
     * @param montant
     * @return
     */
    public static boolean validerMontant(String montant) {

        boolean montantEstValide = false;

        if (montant != null && montant.trim().charAt(montant.trim().length() - 1) == '$') {

            try {
                Double.parseDouble(montant.trim().substring(0, montant.trim().length() - 2));
                montantEstValide = true;

            } catch (NumberFormatException e) {

                montantEstValide = false;
            }
        }
        return montantEstValide;
    }

    /**
     *
     * @author Groupe 15 retourne le montant du remboursement.
     * @param objet
     * @param mois
     * @return
     */
    public static boolean validerLesSoins(JSONObject objet, String mois) {
        int typeSoin = 0;
        boolean soisEsValide = false;
        int tailleDuTableauReclam = objet.getString("reclamation").size();
        String dateDeSoin = "";
        String montantDuSoin =  "";
        JSONArray tableauDesReclam = new JSONArray();
        
               
            for(int i = 0;i < tailleDuTableauReclam;i++) {
              tableauDesReclam.add((JSONObject) objet.getString("reclamation").getJSONobject(i));
              
              typeSoin      =  tableauDesReclam.getJSONObject(i).getString("soin");
              dateDeSoin    =  tableauDesReclam.getJSONObject(i).getString("date");
              montantDuSoin =  tableauDesReclam.getJSONObject(i).getString("montant");
              if (validerMontant(montantDuSoin)
                      && validerLaDate(dateDeSoin,objet.getString("mois"))
                      &&( (typeSoin>=300&&typeSoin<=399)
                      || typeSoin == 0
                      || typeSoin == 100
                      || typeSoin == 200
                      || typeSoin == 400
                      || typeSoin == 500
                      || typeSoin == 600
                      || typeSoin == 700)
                      )
              {
             
                  
                 soisEsValide = true;          
                  
              }
                    
        }
        
            

        return soisEsValide;

    }

    /**
     *
     * @author Groupe 15.
     * @param rembourssement
     * @param uneReclamation
     */
    public static void modifierLeSoin(Double rembourssement, JSONObject uneReclamation) {

        String leMontant = rembourssement + "$";
        uneReclamation.discard("montant");
        uneReclamation.accumulate("montant", leMontant);

    }

    /**
     *
     * @author Groupe 15 retourne le rembourssement du soin calcule selon le
     * contrat, le montant et le numero de soin.
     * @param contrat
     * @param montant
     * @param numSoin
     * @return le rembourcement
     */
    public static Double appliquerLesContrat(String contrat, Double montant, int numSoin) {

         Double rembourssement = 0.0;
         final double TAUX_0_POUR_CENT = 0.00;
         final double TAUX_25_POUR_CENT = 0.25;
         final double TAUX_40_POUR_CENT = 0.4;
         final double TAUX_50_POUR_CENT = 0.5;
         final double TAUX_70_POUR_CENT = 0.7;
         final double TAUX_90_POUR_CENT = 0.9;
         final double TAUX_100_POUR_CENT = 1.00;
        
        switch (contrat) {

            case "A":
                if (numSoin == 0 || numSoin == 100 || numSoin == 200 || numSoin == 500) {
                    rembourssement = montant * TAUX_25_POUR_CENT;

                } else if ((numSoin >= 300 && numSoin <= 399) || (numSoin == 400)
                        || (numSoin == 700)) {
                    rembourssement = montant * TAUX_0_POUR_CENT;

                } else {
                    rembourssement = montant * TAUX_40_POUR_CENT;
                }

                break;

            case "B":

                if (numSoin == 0 || numSoin == 500) {
                    rembourssement = montant * TAUX_50_POUR_CENT;

                    if (rembourssement > 50) {

                        rembourssement = 50.0;
                    }

                } else if ((numSoin >= 300 && numSoin <= 399)) {

                    rembourssement = montant * TAUX_50_POUR_CENT;

                } else if (numSoin == 0) {
                    rembourssement = montant * TAUX_50_POUR_CENT;

                    if (rembourssement > 40) {

                        rembourssement = 40.0;
                    }
                } else if (numSoin == 200) {

                    rembourssement = montant;

                    if (rembourssement > 70) {

                        rembourssement = 70.0;
                    }

                } else if (numSoin == 400) {

                    rembourssement = montant * TAUX_0_POUR_CENT;

                } else if (numSoin == 600) {

                    rembourssement = montant * TAUX_100_POUR_CENT;

                } else {

                    rembourssement = montant * TAUX_70_POUR_CENT;
                }

                break;

            case "C":

                rembourssement = montant * TAUX_90_POUR_CENT;

                break;

            case "D":

                if (numSoin == 200 || numSoin == 600) {

                    rembourssement = montant * TAUX_100_POUR_CENT;

                    if (rembourssement > 100) {

                        rembourssement = 100.0;
                    }

                } else if ((numSoin >= 300 && numSoin <= 399)) {

                    rembourssement = montant * TAUX_100_POUR_CENT;

                } else if (numSoin == 100 || numSoin == 500) {

                    rembourssement = montant * TAUX_100_POUR_CENT;

                    if (rembourssement > 75) {

                        rembourssement = 75.0;
                    }
                } else if (numSoin == 0) {

                    rembourssement = montant * TAUX_100_POUR_CENT;

                    if (rembourssement > 85) {

                        rembourssement = 85.0;
                    }

                } else if (numSoin == 400) {

                    rembourssement = montant * TAUX_100_POUR_CENT;

                    if (rembourssement > 65) {

                        rembourssement = 65.0;
                    }

                } else if (numSoin == 700) {

                    rembourssement = montant * TAUX_100_POUR_CENT;

                    if (rembourssement > 90) {

                        rembourssement = 90.0;
                    }

                }
                break;
        }

        return rembourssement;
    }

    /**
     *
     * @author Groupe 15 retourne la liste des dates des soins.
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

}
