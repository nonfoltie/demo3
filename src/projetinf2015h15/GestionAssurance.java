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
     * retourne le montant du remboursement.
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
     * retourne le montant du remboursement.
     * @param contrat
     * @param numeroSoin
     * @param montant
     * @return 
    */
    public static double remboursement(char contrat,double numeroSoin, double montant) {
        
        double remboursement = 0.00;
        
        if (numeroSoin == 0){
            switch (contrat){
                case 'A':
                    remboursement = (montant * 25)/100;
                break;
                case 'B':
                    remboursement = (montant * 50)/100;
                        if (remboursement > 40){
                            remboursement = 40;
                        } 
                break;
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 85){
                            remboursement = 85;
                        }
                break;
            }
        
        }else if(numeroSoin == 100){
            
            switch (contrat){
                case 'A':
                    remboursement = (montant * 25)/100;
                break;
                case 'B':
                    remboursement = (montant * 50)/100;
                        if (remboursement > 50){
                            remboursement = 50;
                        }
                break;
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 75){
                            remboursement = 75;
                        }
                break;
                    
            
            }
        
        }else if(numeroSoin == 200){
            
                            
            switch (contrat){
                case 'A':
                    remboursement = (montant * 25)/100;
                break;
                case 'B':
                    remboursement = montant;
                        if (remboursement > 70){
                            remboursement = 70;
                        }
                break;
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 100){
                            remboursement = 100;
                        }
                
                break;
            }
        
        }else if(numeroSoin >= 300 && numeroSoin <= 399){
            
              switch (contrat){
                case 'A':
                    remboursement = 0;
                break;
                case 'B':
                    remboursement = (montant * 50)/100;
                break;
                 
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;  
                break;
              }
        
        }else if(numeroSoin == 400){
            
                        
            switch (contrat){
                case 'A':
                    remboursement = 0;
                break;
                case 'B':
                    remboursement = 0; 
                break;
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 65){
                            remboursement = 65;
                        }
                break;
                    
            
            }
        
        }else if(numeroSoin == 500){
            
                switch (contrat){
                case 'A':
                    remboursement = (montant * 25)/100;
                break;
                case 'B':
                    remboursement = (montant * 50)/100;
                        if (remboursement > 50){
                            remboursement = 50;
                        }
                break;
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 75){
                            remboursement = 75;
                        }
                break;
                    
            
            }
        
        }else if(numeroSoin == 600){
            
                        switch (contrat){
                case 'A':
                    remboursement = 0;
                break;
                case 'B':
                    remboursement = 0; 
                break;
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 65){
                            remboursement = 65;
                        }
                break;
                    
            
            }
        
        }else if (numeroSoin == 500){
            
            switch (contrat){
                case 'A':
                    remboursement = (montant * 25)/100;
                break;
                case 'B':
                    remboursement = (montant * 50)/100;
                        if (remboursement > 50){
                            remboursement = 50;
                        }
                break;
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 75){
                            remboursement = 75;
                        }
                break;
                    
            
            }
        
        }else if(numeroSoin == 600){
            
            switch (contrat){
                case 'A':
                    remboursement = (montant * 40)/100;
                break;
                case 'B':
                    remboursement = montant;
                break;
         
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 100){
                            remboursement = 100;
                        }
                break;
                    
            
            }
        
        }else{
            
            switch (contrat){
                case 'A':
                    remboursement = 0;
                break;
                case 'B':
                    remboursement = (montant * 70)/100;
                break;
                
                case 'C':
                    remboursement = (montant * 90)/100;
                break;
                case 'D':
                    remboursement = montant;
                        if (remboursement > 90){
                            remboursement = 90;
                        }
                break;
                    
            
            }
        
        
        }
        
        
        return remboursement;
    }
    
    
     
}
