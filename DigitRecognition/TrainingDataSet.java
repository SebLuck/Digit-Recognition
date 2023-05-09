package DigitRecognition;
/* Class that will get the input and label.*/
import java.util.ArrayList;

public class TrainingDataSet {
    public int inputSize;
    public int outputSize;
    /*ArrayList to store the input data and label.*/
    public ArrayList<double[][]> compareData = new ArrayList<>();

    public TrainingDataSet(int inputSize, int outputSize){
        this.inputSize = inputSize;
        this.outputSize = outputSize;
    }
    /*This function will add the input data and the label to the 
     * multiple-dimensional array compareData. */
    public void add(double[] input, double[] expectedData){
        compareData.add(new double[][]{input, expectedData});
    }
    /*This will choose a certain number of digits each time. For example,
     *  if the batch size is 100, then this function will choose 100 digits
     *   randomly from the train data set and learn them each time until there
     *  is no digit left.*/
    public TrainingDataSet batchExtract(int batchSize){
        TrainingDataSet train = new TrainingDataSet(inputSize, outputSize);
        Integer[] batchArray = RandomArrays.randomValuesBatch(0,
                    this.sizeTrainSet() - 1, batchSize);
        for(Integer index : batchArray){
            train.add(this.getInput(index), this.getOutput(index));
        }
        return train;

    }
    /*Function that returns the total number of data sets that there are 
     * in the train set. */
    public int sizeTrainSet(){
        return compareData.size();
    }
    /*Get the input data from the arraylist compareData*/
    public double[] getInput(int index){
        if(index >= 0 && index < sizeTrainSet())
            return compareData.get(index)[0];
        else return null;
    }
    /*Get the array of label from the arraylist compareData.*/
    public double[] getOutput(int index){
        if(index >= 0 && index < sizeTrainSet()){
            return compareData.get(index)[1];
        }
        else return null;
    }
}
