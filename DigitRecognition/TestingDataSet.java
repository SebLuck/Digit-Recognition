package DigitRecognition;
/*
 * This class will test the train data set.
 * */
public class TestingDataSet {
    /*Function to know how many times the program has made a good prediction. 
     * It will call the outputResult function to display the result of the 
     * algorithm. The result will indicate how well or poorly the network 
     * learned the data.  */
    public static void testTrainingSet(Network network, TrainingDataSet data){
        int correctValue = 0;
        for(int i = 0; i < data.sizeTrainSet(); i++){
            double predictionDigit = highestActivationValue
                    (network.feedForward(data.getInput(i)));
            double expectedDigit = highestActivationValue
                    (data.getOutput(i));
/*If both of these variables are equal, then the correctValue variable is
 *  incremented by 1, which means that the network has made a correct guess.*/
            if(predictionDigit == expectedDigit){
                correctValue++;
            }
        }
        OutputResult.outputResults(correctValue, data.sizeTrainSet());

    }
    
    /*This function will return the expected digit and the predicted 
     * digit made by the network. There is a for loop that will compare 
     * the value inside the outputArray. The index of the highest value will 
     * be the digit. For example, if the index has the highest value, then the
     *  digit will be 1.*/
    public static int highestActivationValue(double[] outputArray){
        int digit = 0;
        for(int i = 1; i < outputArray.length; i++){
            if(outputArray[i] > outputArray[digit]){
                digit = i;
            }
        }
        return digit;
    }
}
