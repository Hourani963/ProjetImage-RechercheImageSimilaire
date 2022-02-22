import fr.unistra.pelican.Image;

import java.io.File;

public class RechercheImage {
    private String pathDossier;
    private ImageOperations image;


    public RechercheImage(String pathDossier, ImageOperations image) throws Exception {
        this.pathDossier = pathDossier;
        this.image = image;
        recherche();
    }

    private void recherche() throws Exception {
        File repertoire = new File(this.pathDossier);
        String liste[] = repertoire.list();

        this.image.getFiltrationMedian();
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                //System.out.println(liste[i]);
                ImageOperations img = new ImageOperations(pathDossier+"\\"+liste[i]);
                img.getFiltrationMedian();
                img.getHistoDiscretisNormalise();
                calculeSimilarite(image.getHistogramDiscretis(), img.getHistogramDiscretis());
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
    }

    private void calculeSimilarite(double[][] histoR, double[][] histoI){
        double distanceR = 0;
        double distanceG = 0;
        double distanceB = 0;

        double distanceTotal=0;
        for(int i=0; i< histoR.length; i++){
            distanceR += Math.pow(histoR[i][0] - histoI[i][0],2);
            distanceG += Math.pow(histoR[i][1] - histoI[i][1],2);
            distanceB += Math.pow(histoR[i][2] - histoI[i][2],2);
        }
        distanceR = Math.sqrt(distanceR);
        distanceG = Math.sqrt(distanceG);
        distanceB = Math.sqrt(distanceB);
        distanceTotal =distanceR+distanceG+distanceB;
        System.out.println(distanceTotal);
    }
}
