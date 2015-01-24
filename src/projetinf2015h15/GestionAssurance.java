/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetinf2015h15;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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
           texteJson = FileReader.loadFileIntoString("resources/inputFile.json", "UTF-8");
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
     * retourne le numero du client.
     * @param objActuel
     * @return
    */
    public static String getNumeroClient(JSONObject objActuel){
        
        
        return null;
    }
    /**
     *
     * @author Groupe 15
     * retourne la catégorie du contrat.
     * @param objActuel
     * @return
    */
    public static String getCategorieContrat(JSONObject objActuel) {
        
        
        return null;
    }
    /**
     *
     * @author Groupe 15
     * retourne le mois.
     * @param objActuel
     * @return
    */
    public static String getMois(JSONObject objActuel){
        
       
        return null;
    }
    /**
     *
     * @author Groupe 15
     * retourne la liste des soins.
     * @param objetJson
     * @return
    */
    public static List<JSONObject> listerLesReclamations(JSONObject objetJson ){
        
        
        return null;
    }
     
    // Validation numero du client
    /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param numero
     * @return
    */
    public static boolean validerNumeroClient(String numero){
        
        
       return false; 
    }
    
   
    /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param contrat
     * @return
    */
    public static boolean validerContrat(String contrat){
        
        
       return false; 
    }
    
    // Validation du mois
    /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param mois
     * @return
    */
     public static boolean validerFormatMois(String mois){
         
        
       return false;
    }
     
    // Validation la date du soin
     /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param date
     * @return
    */
     public static boolean validerLaDate(String date){
         
        
       return false;
    } 
     
    /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param montant
     * @return
    */ 
    public static boolean validerMontant(String montant){
        
        
        return false; 
    } 
    /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param objet
     * @return
    */
    public static boolean validerLesSoins(JSONObject objet){
        
        
        return false;
    
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
    
    /**
     *
     * @author Groupe 15
     * retourne la liste des dates des soins.
     * @param taux
     * @param montant
     * @return
    */
    public static String calculRemboursement(Double taux, String montant) {

        
        return null;
    }
    
    
     
}
