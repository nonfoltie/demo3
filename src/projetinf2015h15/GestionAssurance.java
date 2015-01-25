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
    
    public static final double TAUX_0_POUR_CENT = 0.00;
    public static final double TAUX_25_POUR_CENT = 0.25;
    public static final double TAUX_40_POUR_CENT = 0.4;
    public static final double TAUX_50_POUR_CENT = 0.5;
    public static final double TAUX_70_POUR_CENT = 0.7;
    public static final double TAUX_90_POUR_CENT = 0.9;
    public static final double TAUX_100_POUR_CENT = 1.00;
    
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
            
            while(i < j && (numero.trim().charAt(i) >= '0' && numero.trim().charAt(i) <= '9')){
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
     public static boolean validerFormatMois(String mois){
      boolean formatEstValide = true;
      boolean esNumeric =true;
     if( mois== null ){
         esNumeric = false;
     }else {
      String ch1 = mois.trim().substring(0,4);
      String ch2 = mois.trim().substring(5);
      
             for(int i=0,j=0;i< ch1.length()&& j< ch2.length();i++,j++){
              if(ch1.charAt(i)<'0'||ch1.charAt(i)>'9'||ch2.charAt(j)<'0'||ch2.charAt(j)>'9'){
                  esNumeric = false;
              }
          }
          if( mois.trim().length()!= 7 || mois.trim().substring(4,5).charAt(4)!='-'){
             formatEstValide = false;    
             
          }
      
     }  
     
       return (esNumeric&&formatEstValide );
    }
    
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
     * @param date
     * @param mois
     * @return
    */
     public static boolean validerLaDate(String date, String mois){
         
        boolean reponse = false;
        boolean valide ;
        int i = 0;
        
       if(date != null && date.length() >= 7){
         
           valide = validerFormatMois(date.trim().substring(0,7));
          
           if(valide){
               
        	while (i < mois.length() && (date.charAt(i) == mois.charAt(i))){
                    i++;
        	}      	   
        	reponse = i == mois.length();      		   
           }  	   
       }     
       return reponse;
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
     * @param mois
     * @return
    */
    public static boolean validerLesSoins(JSONObject objet, String mois){
        
        
        return false;
    
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
        uneReclamation.discard("montant");
        uneReclamation.accumulate("montant", leMontant);
        
    }
    
    /**
     *
     * @author Groupe 15
     * retourne le rembourssement du soin calcule selon le contrat, le montant et le numero de soin.
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
                    rembourssement = montant * TAUX_25_POUR_CENT;
                    
                }else if((numSoin >= 300 && numSoin <= 399) || (numSoin == 400) 
                        || (numSoin == 700) ){
                    rembourssement = montant * TAUX_0_POUR_CENT;
                    
                }else{
                    rembourssement = montant * TAUX_40_POUR_CENT;
                }
                    
                break;

            case "B":
                
                if (numSoin == 0 || numSoin == 500 ){
                    rembourssement = montant * TAUX_50_POUR_CENT;
                    
                    if(rembourssement > 50){
                        
                        rembourssement = 50.0;
                    }
                    
                }else if((numSoin >= 300 && numSoin <= 399)  ){
                    
                    rembourssement = montant * TAUX_50_POUR_CENT;
                    
                }else if(numSoin == 0){
                    rembourssement = montant * TAUX_50_POUR_CENT;
                    
                    if(rembourssement > 40){
                        
                        rembourssement = 40.0;
                    }
                }else if (numSoin == 200){
                    
                    rembourssement = montant;
                    
                    if(rembourssement > 70){
                        
                        rembourssement = 70.0;
                    }
                    
                }else if (numSoin == 400){
                    
                    rembourssement = montant * TAUX_0_POUR_CENT;
                    
                }else if (numSoin == 600){
                    
                    rembourssement = montant * TAUX_100_POUR_CENT;
                    
                }else {
                    
                    rembourssement = montant * TAUX_70_POUR_CENT;
                }
                
                break;

            case "C":
                
                rembourssement = montant * TAUX_90_POUR_CENT;
                
                break;

            case "D":
                
                if (numSoin == 200 || numSoin == 600 ){
                    
                    rembourssement = montant * TAUX_100_POUR_CENT;
                    
                    if(rembourssement > 100){
                        
                        rembourssement = 100.0;
                    }
                    
                }else if((numSoin >= 300 && numSoin <= 399)  ){
                    
                    rembourssement = montant * TAUX_100_POUR_CENT ;
                    
                }else if(numSoin == 100 || numSoin == 500){
                    
                    rembourssement = montant * TAUX_100_POUR_CENT;
                    
                    if(rembourssement > 75){
                        
                        rembourssement = 75.0;
                    }
                }else if (numSoin == 0){
                    
                    rembourssement = montant * TAUX_100_POUR_CENT;
                    
                    if(rembourssement > 85){
                        
                        rembourssement = 85.0;
                    }
                    
                }else if (numSoin == 400){
                    
                    rembourssement = montant * TAUX_100_POUR_CENT;
                    
                    if(rembourssement > 65){
                        
                        rembourssement = 65.0;
                    }
                    
                }else if (numSoin == 700){
                    
                    rembourssement = montant * TAUX_100_POUR_CENT;
                    
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
