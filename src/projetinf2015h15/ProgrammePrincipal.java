
package projetinf2015h15;

import java.io.IOException;
import java.util.List;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import static projetinf2015h15.GestionDesCalculs.*;
import static projetinf2015h15.GestionDesFichiers.*;
import static projetinf2015h15.GestionDesOjetsJson.*;
import static projetinf2015h15.GestionDesValidations.*;

public class ProgrammePrincipal {
    
    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            String messageSortie = "Argument(s) manquant(s)";
            gererErreur(messageSortie);
        }
        String fichierEntree = args[0];
        String fichierSortie = args[1];
        traitementReclamations(fichierEntree, fichierSortie);
    }
    
    private static void traitementReclamations(String fichierEntree, String fichierSortie) 
            throws IOException {
        JSONObject objet = testerValiditerDeLobjetJson(fichierEntree);
        List<JSONObject> listeReclamation = calculerRemboursementDuSoin(objet);
        String totalrembourser = additionnerLesRemboursements(listeReclamation);
        String objetJson = creationFichierSortie(objet, listeReclamation, totalrembourser);
        ecrireFichierSurDisque(fichierSortie, objetJson);
        System.out.println(objetJson);
    }

    private static JSONObject testerValiditerDeLobjetJson(String fichierEntree) 
            throws IOException, JSONException {
        JSONObject objet = formaterObjet(chargerFichier(fichierEntree));
        validerLesProprietesJson(objet);
        validerProprietesReclamation(objet.getString("reclamations"));
        validerNumeroDossier(getNumeroDossier(objet));
        validerFormatMois(getMois(objet));
        validerLesSoins(objet,getMois(objet));
        return objet;
    }
    
    private static List<JSONObject> calculerRemboursementDuSoin(JSONObject objet) 
            throws IOException {
        Double leRembourssement;
        List<JSONObject> listeReclamation = listerLesReclamations(objet);
        for (JSONObject uneReclamation : listeReclamation) {
            leRembourssement = appliquerLesContrat(uneReclamation, getNumeroDossier(objet));
            modifierLeSoin(leRembourssement, uneReclamation);
        }
        return listeReclamation;
    }

}
