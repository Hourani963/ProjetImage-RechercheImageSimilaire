import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
            myWriter.write(nomImg+"=>"+Arrays.deepToString(histo) +"*");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFile(){
        try {

            File myObj = new File("indexation.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
