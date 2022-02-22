import fr.unistra.pelican.ByteImage;
import fr.unistra.pelican.Image;
import fr.unistra.pelican.algorithms.io.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ImageOperations {

    private Image image;
    private int largeur;
    private int hauteur;
    private int nbCanaux;

    public ImageOperations(String path) {
        this.image = ImageLoader.exec(path);
        this.largeur = image.getXDim();
        this.hauteur = image.getYDim();
        this.nbCanaux = image.getBDim();
        this.image.setColor(true);
        System.out.println("largeur = " + largeur + " hauteur = "+ hauteur+ " Nombre de Canaux = "+ nbCanaux);
    }
    public Image getImage() {
        return image;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getNbCanaux() {
        return nbCanaux;
    }

    public void getFiltrationMedian() throws Exception{

        ByteImage imageFiltration = new ByteImage(largeur, hauteur, 1, 1, nbCanaux);
        if(nbCanaux == 3 ){
            for(int x=1; x<largeur-2;x++) {
                for (int y = 1; y < hauteur - 2; y++) {
                    // red
                    int r1 = image.getPixelXYBByte(x, y, 1);
                    int r2 = image.getPixelXYBByte(x + 1, y + 1, 1);
                    int r3 = image.getPixelXYBByte(x + 1, y, 1);
                    int r4 = image.getPixelXYBByte(x, y + 1, 1);
                    int r5 = image.getPixelXYBByte(x - 1, y - 1, 1);
                    int r6 = image.getPixelXYBByte(x - 1, y, 1);
                    int r7 = image.getPixelXYBByte(x, y - 1, 1);
                    int r8 = image.getPixelXYBByte(x + 1, y - 1, 1);
                    int r9 = image.getPixelXYBByte(x - 1, y + 1, 1);
                    ArrayList<Integer> rlist = new ArrayList<>();
                    rlist.add(r1);
                    rlist.add(r2);
                    rlist.add(r3);
                    rlist.add(r4);
                    rlist.add(r5);
                    rlist.add(r6);
                    rlist.add(r7);
                    rlist.add(r8);
                    rlist.add(r9);
                    Collections.sort(rlist);
                    imageFiltration.setPixelXYBByte(x, y, 1, rlist.get(4));
                    // green
                    int g1 = image.getPixelXYBByte(x, y, 2);
                    int g2 = image.getPixelXYBByte(x + 1, y + 1, 2);
                    int g3 = image.getPixelXYBByte(x + 1, y, 2);
                    int g4 = image.getPixelXYBByte(x, y + 1, 2);
                    int g5 = image.getPixelXYBByte(x - 1, y - 1, 2);
                    int g6 = image.getPixelXYBByte(x - 1, y, 2);
                    int g7 = image.getPixelXYBByte(x, y - 1, 2);
                    int g8 = image.getPixelXYBByte(x + 1, y - 1, 2);
                    int g9 = image.getPixelXYBByte(x - 1, y + 1, 2);

                    ArrayList<Integer> glist = new ArrayList<>();
                    glist.add(g1);
                    glist.add(g2);
                    glist.add(g3);
                    glist.add(g4);
                    glist.add(g5);
                    glist.add(g6);
                    glist.add(g7);
                    glist.add(g8);
                    glist.add(g9);
                    Collections.sort(glist);
                    imageFiltration.setPixelXYBByte(x, y, 2, glist.get(4));

                    // blue
                    int b1 = image.getPixelXYBByte(x, y, 3);
                    int b2 = image.getPixelXYBByte(x + 1, y + 1, 3);
                    int b3 = image.getPixelXYBByte(x + 1, y, 3);
                    int b4 = image.getPixelXYBByte(x, y + 1, 3);
                    int b5 = image.getPixelXYBByte(x - 1, y - 1, 3);
                    int b6 = image.getPixelXYBByte(x - 1, y, 3);
                    int b7 = image.getPixelXYBByte(x, y - 1, 3);
                    int b8 = image.getPixelXYBByte(x + 1, y - 1, 3);
                    int b9 = image.getPixelXYBByte(x - 1, y + 1, 3);
                    ArrayList<Integer> blist = new ArrayList<>();
                    blist.add(b1);
                    blist.add(b2);
                    blist.add(b3);
                    blist.add(b4);
                    blist.add(b5);
                    blist.add(b6);
                    blist.add(b7);
                    blist.add(b8);
                    blist.add(b9);
                    Collections.sort(blist);
                    imageFiltration.setPixelXYBByte(x, y, 3, blist.get(4));
                }
            }
        }
        else{
            throw new Exception("image doit avoir 3 caneaux");
        }
        imageFiltration.setColor(true);
        this.image = imageFiltration;
    }

    public void getHistogram() throws IOException {
        HistogramTools histogramTools = new HistogramTools();
        double[][] tabHistRGB = new double[256][3];

        for(int x=0; x<largeur-1;x++){
            for(int y=0; y<hauteur-1 ; y++){

                int pixelRed = this.image.getPixelXYBByte(x,y,0);
                int pixelGreen = this.image.getPixelXYBByte(x,y,1);
                int pixelBlue = this.image.getPixelXYBByte(x,y,2);

                tabHistRGB[pixelRed][0]++;
                tabHistRGB[pixelGreen][1]++;
                tabHistRGB[pixelBlue][2]++;

            }
        }

        histogramTools.plotHistogram(histogramTools.normalisationHisto(histogramTools.discretisationHistogram(tabHistRGB),largeur*hauteur));
    }


}
