
package projetinf2015h15;


public class GestionDesCalculs {

    
    public static Double calculerRembourssementTotal() {
        
        return 0.0;
    }
    
    public static Double appliquerLesContrat(String contrat, Double montant, int numSoin) {
        Double rembourssement = 0.0;
        final double TAUX_25_POUR_CENT = 0.25;
        final double TAUX_40_POUR_CENT = 0.4;
        final double TAUX_50_POUR_CENT = 0.5;
        final double TAUX_70_POUR_CENT = 0.7;
        final double TAUX_90_POUR_CENT = 0.9;
        switch (contrat) {
            case "A":
                if (numSoin == 0 || numSoin == 100 || numSoin == 200 || numSoin == 500) {
                    rembourssement = montant * TAUX_25_POUR_CENT;
                } else if ((numSoin >= 300 && numSoin <= 399) || (numSoin == 400) || (numSoin == 700)) {
                    rembourssement = 0.0;
                } else {
                    rembourssement = montant * TAUX_40_POUR_CENT;
                }
                break;
            case "B":
                if (numSoin == 100 || numSoin == 500) {
                    rembourssement = montant * TAUX_50_POUR_CENT;
                    if (rembourssement > 50) {
                        rembourssement = 50.0;
                    }
                } else if (numSoin == 0) {
                    rembourssement = montant * TAUX_50_POUR_CENT;
                    if (rembourssement > 50) {
                        rembourssement = 40.0;
                    }
                } else if (numSoin >= 300 && numSoin <= 399) {
                    rembourssement = montant * TAUX_50_POUR_CENT;
                } else if (numSoin == 200) {
                    rembourssement = montant;
                    if (rembourssement > 70) {
                        rembourssement = 70.0;
                    }
                } else if (numSoin == 400) {
                    rembourssement = 0.0;
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
                        rembourssement = 100.0;
                    }
                } else if (numSoin >= 300 && numSoin <= 399) {
                    rembourssement = montant;
                } else if (numSoin == 100 || numSoin == 500) {
                    rembourssement = montant;
                    if (rembourssement > 75) {
                        rembourssement = 75.0;
                    }
                } else if (numSoin == 0) {
                    rembourssement = montant;
                    if (rembourssement > 85) {
                        rembourssement = 85.0;
                    }
                } else if (numSoin == 400) {
                    rembourssement = montant;
                    if (rembourssement > 65) {
                        rembourssement = 65.0;
                    }
                } else if (numSoin == 700) {
                    rembourssement = montant;
                    if (rembourssement > 90) {
                        rembourssement = 90.0;
                    }
                }
                break;
        }
        return rembourssement;
    }
    
}
