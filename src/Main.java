import fr.unistra.pelican.algorithms.visualisation.Viewer2D;



public class Main {

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        ImageOperations RequeteImage = new ImageOperations("C:\\Users\\ugarit\\Desktop\\GIT\\ProjetImage\\images\\test\\000.jpg");


        RechercheImage rechercheImage = new RechercheImage("C:\\Users\\ugarit\\Desktop\\GIT\\ProjetImage\\images\\motos",RequeteImage);


        long endTime   = System.currentTimeMillis();
        long totalTime = (endTime - startTime)/1000;
        System.err.println("Temps d'exe " + totalTime + "s");

    }
}
