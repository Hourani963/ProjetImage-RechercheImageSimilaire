package com.ahmad.projetImageBackEnd.algosJava;

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
            FileWriter myWriter = new FileWriter(nomFile + ".txt",true);
            double a = 0;
            myWriter.write(nomImg+"=>"+Arrays.deepToString(histo) +"//");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // cette méthode rétourne tous les histogram de toutes les photos
    // [0]=> pour les valeur de l'histogram parmis les 26 valeurs/ [1]=> pour la valeur RGB/ [2]=> pour l'image donnée/ pour récupérer le nom de l'image à la fin il faut utilisé [2]
    public double[][][] readFile(){
        try {

            String text = Files.readString(Paths.get(nomFile + ".txt"));
            //System.out.println(text);
            String[] allInfo = text.split("//");

            double[][][] histos = new double[26][3][allInfo.length];
            for(int i=0; i<allInfo.length; i++){

                String[] oneImage = allInfo[i].split("=>");

                //System.out.println(oneImage[0]);//image name
                StringBuilder sb = new StringBuilder(oneImage[1]);
                //System.out.println(oneImage[1]);
                sb.deleteCharAt(0); //delete fist [
                sb.deleteCharAt(sb.length()-1); // delete last ]
                sb.deleteCharAt(sb.length()-1); // delete last ]
                oneImage[1]= sb.toString();
                for(int j=0; j<26 ; j++){
                    String[] spiltHistoA = oneImage[1].split("],");
                    StringBuilder sb2 = new StringBuilder(spiltHistoA[j]);
                    sb2.deleteCharAt(0);
                    if(j!=0) sb2.deleteCharAt(0);
                    spiltHistoA[j] = sb2.toString();
                    //System.out.println(spiltHistoA[j]);
                    for(int x=0; x<3; x++){
                        String[] spiltHistoB = spiltHistoA[j].split(", ");
                        //System.out.println(spiltHistoB[x]);
                        histos[j][x][i] = Double.parseDouble(spiltHistoB[x]);
                    }
                }

            }

            return histos;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
