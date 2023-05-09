package DigitRecognition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*Class that will read the csv file of the training and test data for
 *  the neural network algorithm.*/
public class ReadFileNN {
    
    //Function that reads the neural network algorithm's training csv file.
    public static TrainingDataSet createTrainSet(String trainDataFile){
        TrainingDataSet train = new TrainingDataSet(64, 10);
        try{
            Scanner reader = new Scanner(new File(trainDataFile));
            while(reader.hasNextLine()){
                String lineReader = reader.nextLine();	

                int lastIndex = lineReader.lastIndexOf(',');
                int labelDigit = Integer.parseInt(lineReader.substring
                        (lastIndex +1, lastIndex +2));
                String lineNew = lineReader.substring(0, lastIndex);
                String[] lineSplit = lineNew.split(",");

                double[] digitList = new double[64];
                for(int i=0; i < 64; i++){
                    digitList[i] = Double.parseDouble
                            (lineSplit[i])/ (double)16;
                }
                double[] labelList = new double[10];
                labelList[labelDigit] = 1d;
                train.add(digitList, labelList);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("Error. The file could not be found.");
            e.printStackTrace();
        }
        return train;
    }
    //Function that reads the neural network algorithm's test csv file.
    public static TrainingDataSet createTestSet(String testDataFile) {
        TrainingDataSet test = new TrainingDataSet(64, 10);
        try {
            Scanner reader = new Scanner(new File(testDataFile));  	
            while (reader.hasNextLine()) {
                String lineReader = reader.nextLine();

                int lastIndex = lineReader.lastIndexOf(',');
                int labelDigit = Integer.parseInt(lineReader.substring
                        (lastIndex + 1, lastIndex + 2));
                String lineNew = lineReader.substring(0, lastIndex);
                String[] lineSplit = lineNew.split(",");

                double[] digitList = new double[64];
                for (int i = 0; i < 64; i++) {
                    digitList[i] = Double.parseDouble
                            (lineSplit[i]) / (double)16;
                }
                double[] labelList = new double[10];
                labelList[labelDigit] = 1d;
                test.add(digitList, labelList);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error. The file could not be found.");
            e.printStackTrace();
        }
        return test;
    }
}
