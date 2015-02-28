
package projetinf2015h15;

import net.sf.json.JSONObject;


public class GestionDesCalculs {

    
    public static Double calculerRembourssementTotal() {
        
        return 0.0;
    }
    
    public static Double appliquerLesContrat(JSONObject uneReclamation, String dossier) {
        Double remboursement = 0.0;
        int numSoin = uneReclamation.getInt("soin");
        String chaineMontant = uneReclamation.getString("montant");
        int tailleDuMontant = chaineMontant.trim().length();
        Double montant = Double.parseDouble(chaineMontant.substring(0, tailleDuMontant- 1));
        
        switch (dossier.substring(0, 1)) {
            case "A":
                
                remboursement = rembourserContratA(numSoin, montant);
                break;
            case "B":
                remboursement = rembourserContratB(numSoin, montant);
                break;
            case "C":
                remboursement = rembourserContratC(numSoin, montant);
                break;
            case "D":
                remboursement = rembourserContratD(numSoin, montant);
                break;
            case "E":
                remboursement = rembourserContratE(numSoin, montant);
        }
        return remboursement;
    }

    private static Double rembourserContratE(int numSoin, Double montant) {
        Double remboursement;
        if (numSoin == 0 || numSoin == 600 || numSoin == 150) {
            remboursement = montant * 0.15;
        } else if (numSoin >= 300 && numSoin <= 399) {
            remboursement = montant *0.6;
        } else if (numSoin == 100) {
            remboursement = montant * 0.25;
        } else if (numSoin == 200) {
            remboursement = montant * 0.12;
        } else if (numSoin == 400) {
            remboursement = montant *0.25;
            if (remboursement > 15) {
                remboursement = 15.0;
            }
        } else if (numSoin == 500) {
            remboursement = montant * 0.30;
            if (remboursement > 20) {
                remboursement = 20.0;
            }
        } else if (numSoin == 175) {
            remboursement = montant * 0.25;
            if (remboursement > 20) {
                remboursement = 20.0;
            }
        }else {
            remboursement = montant * 0.22;
        }
        return remboursement;
    }

    private static Double rembourserContratD(int numSoin, Double montant) {
        Double remboursement = 0.0;
        if (numSoin == 200 || numSoin == 600) {
            remboursement = montant;
            if (remboursement > 100) {
                remboursement = 100.0;
            }
        } else if (numSoin >= 300 && numSoin <= 399) {
            remboursement = montant;
        } else if (numSoin == 100) {
            remboursement = montant;
            if (remboursement > 75) {
                remboursement = 75.0;
            }
        } else if (numSoin == 0) {
            remboursement = montant;
            if (remboursement > 85) {
                remboursement = 85.0;
            }
        } else if (numSoin == 400) {
            remboursement = montant;
            if (remboursement > 65) {
                remboursement = 65.0;
            }
        } else if (numSoin == 700) {
            remboursement = montant;
            if (remboursement > 90) {
                remboursement = 90.0;
            }
        } else if (numSoin == 150) {
            remboursement = montant;
            if (remboursement > 150) {
                remboursement = 150.0;
            }
        }else if (numSoin == 175) {
            remboursement = montant * 0.95;
        } else if (numSoin == 500) {
            remboursement = montant;
        }
        return remboursement;
    }
    private static Double rembourserContratC(int numSoin, Double montant) {
        Double remboursement;
        if (numSoin == 150 ) {
            remboursement = montant * 0.85;
        } else if (numSoin == 100) {
            remboursement = montant * 0.95;
        } else if (numSoin == 600) {
            remboursement = montant * 0.75;
        } else {
            remboursement = montant * 0.9;
        }
        return remboursement;
    }
    private static Double rembourserContratB(int numSoin ,Double montant) {
        Double remboursement;
        if (numSoin == 100 || numSoin == 500) {
            remboursement = montant * 0.5;
            if (remboursement > 50) {
                remboursement = 50.0;
            }
        } else if (numSoin == 0) {
            remboursement = montant * 0.5;
            if (remboursement > 40) {
                remboursement = 40.0;
            }
        } else if (numSoin >= 300 && numSoin <= 399) {
            remboursement = montant * 0.5;
        } else if (numSoin == 200) {
            remboursement = montant;
        } else if (numSoin == 175) {
            remboursement = montant * 0.75;
        } else if (numSoin == 400 || numSoin == 150) {
            remboursement = 0.0;
        } else if (numSoin == 600) {
            remboursement = montant;
        } else {
            remboursement = montant * 0.7;
        }
        return remboursement;
    }

    private static Double rembourserContratA(int numSoin, Double montant) {
        Double remboursement;
        if (numSoin == 0 || numSoin == 200 || numSoin == 500) {
            remboursement = montant * 0.25;
        } else if ((numSoin >= 300 && numSoin <= 399) || (numSoin == 400) 
                || (numSoin == 700) || (numSoin == 150)) {
            remboursement = 0.0;
        } else if (numSoin == 175){
            remboursement = montant * 0.5;
        } else if (numSoin == 100){
            remboursement = montant * 0.35;
        } else {
            remboursement = montant * 0.4;
        }
        return remboursement;
    }
}
