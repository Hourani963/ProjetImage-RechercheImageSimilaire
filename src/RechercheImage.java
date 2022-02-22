import fr.unistra.pelican.Image;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class RechercheImage {
    private String pathDossier;
    private ImageOperations image;

    TreeMap<Double, String> tree_map = new TreeMap<Double, String>();

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
                img.setNom(liste[i]);
                img.getFiltrationMedian();
                img.getHistoDiscretisNormalise();
                //************ Calcculer
                calculeSimilarite(image, img);
                //************ Organiser
                tree_map.put(img.getValeurSimilarite(),img.getNom());
            }
            System.out.println(tree_map);
        } else {
            System.err.println("Nom de repertoire invalide");
        }
    }

    private void calculeSimilarite(ImageOperations imageR, ImageOperations imageI) throws IOException {
        double distanceR = 0;
        double distanceG = 0;
        double distanceB = 0;

        double distanceTotal=0;
        for(int i=0; i< imageR.getHistoDiscretisNormalise().length; i++){
            distanceR += Math.pow(imageR.getHistoDiscretisNormalise()[i][0] - imageI.getHistoDiscretisNormalise()[i][0],2);
            distanceG += Math.pow(imageR.getHistoDiscretisNormalise()[i][1] - imageI.getHistoDiscretisNormalise()[i][1],2);
            distanceB += Math.pow(imageR.getHistoDiscretisNormalise()[i][2] - imageI.getHistoDiscretisNormalise()[i][2],2);
        }
        distanceR = Math.sqrt(distanceR);
        distanceG = Math.sqrt(distanceG);
        distanceB = Math.sqrt(distanceB);
        distanceTotal =distanceR+distanceG+distanceB;
        imageI.setValeurSimilarite(distanceTotal);
        imageR.setValeurSimilarite(0);
        System.out.println(distanceTotal);
    }
}
