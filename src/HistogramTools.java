import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class HistogramTools {


    public void plotHistogram(double [][] histogramRGB) throws IOException{

        XYSeries myseriesRed = new XYSeries("Nombre de pixels RED");
        XYSeries myseriesGreen = new XYSeries("Nombre de pixels GREEN");
        XYSeries myseriesBlue = new XYSeries("Nombre de pixels BLUE");
        for(int i=0;i<histogramRGB.length;i++){
            myseriesRed.add(new Double(i), new Double(histogramRGB[i][0]));
            myseriesGreen.add(new Double(i), new Double(histogramRGB[i][1]));
            myseriesBlue.add(new Double(i), new Double(histogramRGB[i][2]));

        }
        XYSeriesCollection myseriescollectionRed = new XYSeriesCollection(myseriesRed);
        XYSeriesCollection myseriescollectionGreen = new XYSeriesCollection(myseriesGreen);
        XYSeriesCollection myseriescollectionBlue = new XYSeriesCollection(myseriesBlue);

        //******
        JFreeChart jfreechartRed = ChartFactory.createXYBarChart("Histogramme de l'image", "RED", false, "Nombre de pixels", myseriescollectionRed, PlotOrientation.VERTICAL, true, false, false);
        jfreechartRed.setBackgroundPaint(Color.white);
        XYPlot xyplotRed = jfreechartRed.getXYPlot();
        JFreeChart jfreechartGreen = ChartFactory.createXYBarChart("Histogramme de l'image", "GREEN", false, "Nombre de pixels", myseriescollectionGreen, PlotOrientation.VERTICAL, true, false, false);
        jfreechartGreen.setBackgroundPaint(Color.white);
        XYPlot xyplotGreen = jfreechartGreen.getXYPlot();
        JFreeChart jfreechartBlue = ChartFactory.createXYBarChart("Histogramme de l'image", "BLUE", false, "Nombre de pixels", myseriescollectionBlue, PlotOrientation.VERTICAL, true, false, false);
        jfreechartBlue.setBackgroundPaint(Color.white);
        XYPlot xyplotBlue = jfreechartBlue.getXYPlot();

        //*******
        xyplotRed.setBackgroundPaint(Color.lightGray);
        xyplotRed.setRangeGridlinePaint(Color.RED);
        NumberAxis axisRed = (NumberAxis) xyplotRed.getDomainAxis();
        axisRed.setLowerMargin(0);
        axisRed.setUpperMargin(0);
        xyplotGreen.setBackgroundPaint(Color.lightGray);
        xyplotGreen.setRangeGridlinePaint(Color.green);
        NumberAxis axisGreen = (NumberAxis) xyplotGreen.getDomainAxis();
        axisGreen.setLowerMargin(0);
        axisGreen.setUpperMargin(0);
        xyplotBlue.setBackgroundPaint(Color.lightGray);
        xyplotBlue.setRangeGridlinePaint(Color.blue);
        NumberAxis axisBlue = (NumberAxis) xyplotBlue.getDomainAxis();
        axisBlue.setLowerMargin(0);
        axisBlue.setUpperMargin(0);
        //**********

        // create and display a frame...
        ChartFrame frameRed = new ChartFrame("DUT 2 Informatique - Image", jfreechartRed);
        frameRed.pack();
        frameRed.setVisible(true);
        ChartFrame frameGreen = new ChartFrame("DUT 2 Informatique - Image", jfreechartGreen);
        frameGreen.pack();
        frameGreen.setVisible(true);
        ChartFrame frameBlue = new ChartFrame("DUT 2 Informatique - Image", jfreechartBlue);
        frameBlue.pack();
        frameBlue.setVisible(true);
    }

    public double[][] discretisationHistogram(double [][]histogramRGB)throws IOException{
        double[][] discretisationHistRGB = new double[26][3];
        double sommeValeursR = 0;
        double sommeValeursG = 0;
        double sommeValeursB = 0;
        int count  = 0;
        for(int i = 0 ; i<histogramRGB.length;i++){
            sommeValeursR += histogramRGB[i][0];  //faire la somme de pixels
            sommeValeursG += histogramRGB[i][1];
            sommeValeursB += histogramRGB[i][2];
            if(i%10 == 0){
                sommeValeursR /= 10;  // déviser la somme par 10 pour touver la moyenne
                sommeValeursG /= 10;
                sommeValeursB /= 10;

                discretisationHistRGB[count][0] = sommeValeursR;  // insérer les valeurs dans le nouveau tableau
                discretisationHistRGB[count][1] = sommeValeursG;
                discretisationHistRGB[count][2] = sommeValeursB;
                count++;

                sommeValeursR=0; // remettre les variable à 0 pour recalculer la nouvelle moyenne
                sommeValeursG=0;
                sommeValeursB =0;
            }
        }
        return discretisationHistRGB;
    }


    public static void saveHistogram(double [] histogram, String pathToSave) throws IOException{

        XYSeries myseries = new XYSeries("Nombre de pixels");
        for(int i=0;i<histogram.length;i++){
            myseries.add(new Double(i), new Double(histogram[i]));
        }
        XYSeriesCollection myseriescollection = new XYSeriesCollection(myseries);

        JFreeChart jfreechart = ChartFactory.createXYBarChart("Histogramme de l'image", "Niveaux de gris", false, "Nombre de pixels", myseriescollection, PlotOrientation.VERTICAL, true, false, false);
        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = jfreechart.getXYPlot();

        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setRangeGridlinePaint(Color.white);
        NumberAxis axis = (NumberAxis) xyplot.getDomainAxis();

        axis.setLowerMargin(0);
        axis.setUpperMargin(0);

        if(pathToSave!=null)
            ChartUtilities.saveChartAsPNG(new File(pathToSave), jfreechart, 900, 600);
    }


}