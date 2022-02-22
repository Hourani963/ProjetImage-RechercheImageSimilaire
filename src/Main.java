import fr.unistra.pelican.algorithms.visualisation.Viewer2D;

public class Main {

    public static void main(String[] args) throws Exception {

        ImageOperations img1 = new ImageOperations("C:\\Users\\UGARIT\\Desktop\\AhmadAndFadiMedian.jpg");
        //img1.getFiltrationMedian();
        img1.getHistogram();
        //Viewer2D.exec(img1.getImage());

    }
}
