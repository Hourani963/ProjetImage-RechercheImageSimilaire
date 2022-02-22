import fr.unistra.pelican.algorithms.visualisation.Viewer2D;



public class Main {

    public static void main(String[] args) throws Exception {

        FileStorage fileStorage = new FileStorage();
        ImageOperations RequeteImage = new ImageOperations("C:\\Users\\UGARIT\\Desktop\\GIT\\ProjetImage\\images\\test\\AhmadAndFadiMedian.jpg");


        // Doit rechercher dans MySQL et fair une méthod pour actualiser la base de donnée
        RechercheImage rechercheImage = new RechercheImage("C:\\Users\\UGARIT\\Desktop\\GIT\\ProjetImage\\images\\test",RequeteImage);




    }
}
