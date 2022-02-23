import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class FileStorage {

    private String nomFile;

    public FileStorage(String nomFile) {
        this.nomFile = nomFile;
        createFile();
    }

    private void createFile(){
        try {
            File myObj = new File(nomFile + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeFile(String nomImg, double[][] histo){
        try {
            FileWriter myWriter = new FileWriter("indexation.txt",true);
            double a = 0;
            myWriter.write(nomImg+"=>"+Arrays.deepToString(histo) +"//");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFile(){
        try {
            String text = Files.readString(Paths.get("indexation.txt"));
            System.out.println(text);
            String[] allInfo = text.split("//");
            for(int i=0; i<allInfo.length; i++){
                String[] oneImage = allInfo[i].split("=>");
                System.out.println(oneImage[0]);//image name
                System.out.println(oneImage[1]);// image diagram
            }
            /*File myObj = new File("indexation.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataA = data.split("//");
                String[] dataB = dataA[1].split("=>");
                System.out.println(dataB[0]);
            }
            myReader.close();*/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
