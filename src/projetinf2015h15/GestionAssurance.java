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
     * @author Groupe 15
     * Changement du fichier d'entrée.
     * @return 
     * @throws java.io.IOException
    */
     public static String chargerFichier() throws IOException{
        
        String texteJson = null;
        
        try{
           texteJson = FileReader.loadFileIntoString("resources/inputFile.json","UTF-8");
        } catch (FileNotFoundException e){
            return texteJson;
        } catch (Exception e){
            return texteJson;
        }
        
        return texteJson;
    }
    /**
     *
     * @author Groupe 15
     * Création de l'objet.
     * @return 
     * @throws java.io.IOException
    */
    public static JSONObject formaterObjet() throws IOException{
        
        String leTexte = chargerFichier();
        JSONObject objActuel = null;
        
        if (leTexte != null){
            
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
    public static String getNumeroClient(JSONObject objActuel){
        
        String numeroClient = null;
        
        if (objActuel != null){
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
        
        if (objActuel != null){
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
    public static String getMois(JSONObject objActuel){
        
       String leMois = null;
        
        if (objActuel != null){
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
    public static List<JSONObject> listerLesReclamations(JSONObject objetJson ){
        
        List<JSONObject> listeReclamation = null;
        
        if (objetJson != null){
            
            listeReclamation = new ArrayList<>();
            String reclamation = objetJson.getString("reclamations");
            JSONArray tableauReclamation = (JSONArray) JSONSerializer.toJSON(reclamation);
        
            for (int i = 0; i < tableauReclamation.size(); i++){
            
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
    public static boolean validerNumeroClient(String numero){
        
        boolean reponse = false;
        int i = 0;
        int j ;
        
        if(numero != null && numero.trim().length() == 6){
            
            j = numero.trim().length();
            
            while(i < j && (numero.charAt(i) >= '0' && numero.charAt(i) <= '9')){
               i++;
            }  
            reponse = i == j;
        }
       return reponse; 
    }
    
   
    /**
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param contrat
     * @return vrai si le contrat est valide. Sinon, faux.
    */
    public static boolean validerContrat(String contrat){
        
        boolean reponse = false;

        if(contrat != null && (contrat.equals("A")|| contrat.equals("B")
                || contrat.equals("C") || contrat.equals("D"))){
            
            reponse = true;
        }    
         
       return reponse;
    }
    
    /**
     * @author Groupe 15
     * @param mois
     * @return vrai si la date est valide. Sinon, faux.
    */
    
    
    /**
     * @author Groupe 15
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
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param rembourssement
     * @param uneReclamation 
    */
    public static void modifierLeSoin(Double rembourssement,JSONObject uneReclamation){
        
        String leMontant = rembourssement + "$";
        uneReclamation.accumulate("montant", leMontant);
        
    }
    
    /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param contrat
     * @param montant
     * @param numSoin
     * @return le rembourcement
    */
    public static Double appliquerLesContrat(String contrat, Double montant, int numSoin) {
        
        Double rembourssement = 0.0;
        switch (contrat) {

            case "A":
                if (numSoin == 0 ||numSoin == 100 || numSoin == 200 || numSoin == 500 ){
                    rembourssement = montant * 0.25;
                    
                }else if((numSoin >= 300 && numSoin <= 399) || (numSoin == 400) 
                        || (numSoin == 700) ){
                    rembourssement = 0.0;
                    
                }else{
                    rembourssement = montant * 0.4;
                }
                    
                break;

            case "B":
                
                if (numSoin == 0 || numSoin == 500 ){
                    rembourssement = montant * 0.5;
                    
                    if(rembourssement > 50){
                        
                        rembourssement = 50.0;
                    }
                    
                }else if((numSoin >= 300 && numSoin <= 399)  ){
                    
                    rembourssement = montant * 0.5;
                    
                }else if(numSoin == 0){
                    rembourssement = montant * 0.5;
                    
                    if(rembourssement > 40){
                        
                        rembourssement = 40.0;
                    }
                }else if (numSoin == 200){
                    
                    rembourssement = montant;
                    
                    if(rembourssement > 70){
                        
                        rembourssement = 70.0;
                    }
                    
                }else if (numSoin == 400){
                    
                    rembourssement = 0.0;
                    
                }else if (numSoin == 600){
                    
                    rembourssement = montant;
                    
                }else {
                    
                    rembourssement = montant * 0.7;
                }
                
                break;

            case "C":
                
                rembourssement = montant * 0.9;
                
                break;

            case "D":
                
                if (numSoin == 200 || numSoin == 600 ){
                    
                    rembourssement = montant;
                    
                    if(rembourssement > 100){
                        
                        rembourssement = 100.0;
                    }
                    
                }else if((numSoin >= 300 && numSoin <= 399)  ){
                    
                    rembourssement = montant ;
                    
                }else if(numSoin == 100 || numSoin == 500){
                    
                    rembourssement = montant;
                    
                    if(rembourssement > 75){
                        
                        rembourssement = 75.0;
                    }
                }else if (numSoin == 0){
                    
                    rembourssement = montant;
                    
                    if(rembourssement > 85){
                        
                        rembourssement = 85.0;
                    }
                    
                }else if (numSoin == 400){
                    
                    rembourssement = montant;
                    
                    if(rembourssement > 65){
                        
                        rembourssement = 65.0;
                    }
                    
                }else if (numSoin == 700){
                    
                    rembourssement = montant;
                    
                    if(rembourssement > 90){
                        
                        rembourssement = 90.0;
                    }
                    
                }
                break;
        }

        return rembourssement;
    }
    /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
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
