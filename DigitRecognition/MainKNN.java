package DigitRecognition;
/*
 * This is the main class that will run the
 * K Nearest Neighbour Algorithm.
 */

import java.io.FileNotFoundException;

public class MainKNN {
    public static String dataSet1 = "src/DigitRecognition/cw2DataSet1.csv";
    public static String dataSet2 = "src/DigitRecognition/cw2DataSet2.csv";
	public static void main(String[] args) {
		try {
			KNearestNeighbour nearestNeighbour = new KNearestNeighbour();
	        System.out.println("KNN algorithm is running...");
	        System.out.println("-----------------------------");
	        System.out.println("Training dataset 1 and testing dataset 2");
	        System.out.println();
	        nearestNeighbour.KNNMethod(dataSet1, dataSet2);
	        System.out.println();
	        System.out.println("-----------------------------");
	        System.out.println("Training dataset 2 and testing dataset 1");
	        nearestNeighbour.KNNMethod(dataSet2, dataSet1);
	        System.out.println();
	        System.out.println("-----------------------------");
		}
        catch (FileNotFoundException e) {
            System.out.println("Error. The file could not be found");
            e.printStackTrace();
        }
	}

}
