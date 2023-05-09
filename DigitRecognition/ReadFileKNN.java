package DigitRecognition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*Class that will read the csv file of the training and test data for
 *  the K-Nearest Neighbour algorithm.*/
public class ReadFileKNN {
	/*
     * Function that reads the KNN algorithm's training csv file.
     *  It will return a list of train numbers.
     */
    public List<Digit> trainData(String trainDataFile) {
        List<Digit> trainDataList = new ArrayList<>();
        // Read the training file and create a list to store the training digits.
        try{
            Scanner reader = new Scanner(new File(trainDataFile));
            while(reader.hasNextLine()){
                String lineReader = reader.nextLine();

                // This variable will store the index of the last comma.
                int lastIndex = lineReader.lastIndexOf(',');
                // Get the label of the digit by using the lastIndex variable.
                int labelDigit = Integer.parseInt(lineReader.substring
                        (lastIndex +1, lastIndex +2));
                // Split the line by a comma to get the rest of the line.
                String lineNew = lineReader.substring(0, lastIndex);
                String[] lineSplit = lineNew.split(",");

                // Convert string train digits to integers. 
                ArrayList<Integer> digitList = new ArrayList<>();
                for(int i = 0; i < lineSplit.length; i++){
                    digitList.add(Integer.parseInt(lineSplit[i]));
                }
                /* The Digit class is used to create a digit object, 
                 * which is then stored in the trainDataList. */
                trainDataList.add(new Digit(digitList, labelDigit));
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Error. The file could not be found.");
            e.printStackTrace();
        }
        return trainDataList;
    }

    /*
     * Function that reads the test csv file for the KNN algorithm. 
     * It will return a list of test digits.
     */
    public List<Digit> testData(String testDataFile) {
        List<Digit> testDataList = new ArrayList<>();
        // Read the test file and create a list to store the test digits.
        try{
            Scanner reader = new Scanner(new File(testDataFile));
            while(reader.hasNextLine()){
                String lineReader = reader.nextLine();
                int lastIndex = lineReader.lastIndexOf(',');
                int labelDigit = Integer.parseInt(lineReader.substring
                        (lastIndex +1, lastIndex +2));
                String lineNew = lineReader.substring(0, lastIndex);
                String[] lineSplit = lineNew.split(",");

                ArrayList<Integer> digitList = new ArrayList<>();

                for(int i = 0; i < lineSplit.length; i++){
                    digitList.add(Integer.parseInt(lineSplit[i]));
                }

                testDataList.add(new Digit(digitList, labelDigit));
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("Error. The file could not be found.");
            e.printStackTrace();
        }
        return testDataList;
    }
}
