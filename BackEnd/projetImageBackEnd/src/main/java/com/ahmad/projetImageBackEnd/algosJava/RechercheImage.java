package com.ahmad.projetImageBackEnd.algosJava;

import fr.unistra.pelican.Image;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class RechercheImage {
    private Integer[] bestImages = new Integer[10];
    public static String[] bestImagesFullPath = new String[10];
    public static String pathDossier;
    private ImageOperations image;
    private File repertoir;
    private int nombreImagesIndexation;
    TreeMap<Double, String> tree_map = new TreeMap<Double, String>();
    TreeMap<Double, Integer> tree_mapIndexation = new TreeMap<Double, Integer>();
    private FileStorage fileStorage = new FileStorage("indexation");
    private FileStorage fileStorageHSB = new FileStorage("indexationHSB");


    public RechercheImage(String pathDossier, ImageOperations imagee) throws Exception {
        this.pathDossier = pathDossier;
        image = imagee;
        this.repertoir = new File(this.pathDossier);
        partieB();
    }
    public void partieA() throws Exception {
        recherche();

    }
    public void partieB() throws Exception {
        //setIndexation(fileStorage);
        rechercheIndexation(fileStorage);
    }
    public void partieC() throws Exception {
        //setIndexation(fileStorageHSB);
        //rechercheHSB();
        rechercheIndexation(fileStorageHSB);
    }

    private void setIndexation(FileStorage file) throws Exception {
        String listeA[] = repertoir.list();
        // crée un ficier pour le stockage

        this.nombreImagesIndexation = listeA.length;
        if (listeA != null) {
            for (int i = 0; i < listeA.length; i++) {
                //System.out.println(liste[i]);
                ImageOperations img = new ImageOperations(pathDossier + "\\" + listeA[i]);
                img.setNom(listeA[i]);
                img.getFiltrationMedian();
                img.getHistoDiscretisNormalise();
                file.writeFile(img.getNom(), img.getJusthistoDiscretiNormalise());
            }
            System.out.println("Successfully wrote to the file.");
        }
    }
    private void rechercheIndexation(FileStorage file) throws Exception {

        String listeA[] = this.repertoir.list();///////////////////************** to delete
        this.nombreImagesIndexation = listeA.length;///////////////// to delete


        image.getFiltrationMedian();
        image.getHistoDiscretisNormalise();
        double[][][] storageHistogramIndexation;
        storageHistogramIndexation = file.readFile();
        calculeSimilariteIndexation(image,storageHistogramIndexation);

    }

    private void recherche() throws Exception {
        String liste[] = repertoir.list();

        image.getFiltrationMedian();
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                //System.out.println(liste[i]);
                ImageOperations img = new ImageOperations(pathDossier+"\\"+liste[i]);
                img.setNom(liste[i]);
                img.getFiltrationMedian();
                img.getHistoDiscretisNormalise();
                //************ Calculer
                calculeSimilarite(image, img);
                //************ Organiser
                tree_map.put(img.getValeurSimilarite(),img.getNom());
            }
            //************ Sort TreeMap
            entriesSortedByValues(tree_map);
            //************ get first 10 elements
            getMeuilleurImagesSimilaire(tree_map);
            //System.out.println(tree_map);
        } else {
            System.err.println("Nom de repertoire invalide");
        }
    }
    private void rechercheHSB() throws Exception {
        String liste[] = repertoir.list();

        image.getFiltrationMedian();
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                //System.out.println(liste[i]);
                ImageOperations img = new ImageOperations(pathDossier+"\\"+liste[i]);
                img.setNom(liste[i]);
                img.getFiltrationMedian();
                img.getHistoDiscretisNormaliseHSB();
                //************ Calculer
                calculeSimilarite(image, img);
                //************ Organiser
                tree_map.put(img.getValeurSimilarite(),img.getNom());
            }
            //************ Sort TreeMap
            entriesSortedByValues(tree_map);
            //************ get first 10 elements
            getMeuilleurImagesSimilaire(tree_map);
            //System.out.println(tree_map);

        } else {
            System.err.println("Nom de repertoire invalide");
        }
    }

    private void calculeSimilariteIndexation(ImageOperations imageR, double[][][] histogramsToutesImages) throws IOException {
        System.err.println(nombreImagesIndexation);
        String liste[] = repertoir.list();
        for(int i=0; i<this.nombreImagesIndexation; i++){
            double distanceR = 0;
            double distanceG = 0;
            double distanceB = 0;
            double distanceTotal=0;
            for(int j=0; j< imageR.getJusthistoDiscretiNormalise().length; j++){
                distanceR += Math.pow(imageR.getJusthistoDiscretiNormalise()[j][0] - histogramsToutesImages[j][0][i],2);
                distanceG += Math.pow(imageR.getJusthistoDiscretiNormalise()[j][1] - histogramsToutesImages[j][1][i],2);
                distanceB += Math.pow(imageR.getJusthistoDiscretiNormalise()[j][2] - histogramsToutesImages[j][2][i],2);
            }
            distanceR = Math.sqrt(distanceR);
            distanceG = Math.sqrt(distanceG);
            distanceB = Math.sqrt(distanceB);
            distanceTotal =distanceR+distanceG+distanceB;
            System.err.println("La distance total est "+ distanceTotal);
            tree_mapIndexation.put(distanceTotal,i);
        }
        entriesSortedByValues(tree_mapIndexation);
        //************ get first 10 elements
        getMeuilleurImagesSimilaire(tree_mapIndexation);
        //System.out.println(tree_mapIndexation);
        //TreeMap
        for(int i =0; i<10; i++){
            Image[] images = new Image[10];
            assert liste != null;
            System.out.println(pathDossier+"\\"+liste[bestImages[i]]);
            bestImagesFullPath[i] =liste[bestImages[i]];
            //images[i] = ImageLoader.exec(pathDossier+"\\"+liste[bestImages[i]]);
            //Viewer2D.exec(images[i]);
        }

    }

    public String[] getBestImagesFullPath() {
        return bestImagesFullPath;
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
        //System.out.println(distanceTotal);
    }

    private void getMeuilleurImagesSimilaire(TreeMap map){
        // ICI JE Choisie les meuillers 10 images
        SortedMap<Double, String> firstTen = putFirstEntries(10, map);
        //System.err.println(firstTen);
    }


    private static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
    private <V, K> SortedMap<K,V> putFirstEntries(int max, SortedMap<K,V> source) {
        int count = 0;
        TreeMap<K,V> target = new TreeMap<K,V>();
        for (Map.Entry<K,V> entry:source.entrySet()) {
            if (count >= max) break;

            this.bestImages[count] = (Integer) entry.getValue();
            target.put(entry.getKey(), entry.getValue());
            count++;
        }
        return target;
    }

}
