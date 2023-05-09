package DigitRecognition;
/*
 * This class will create random arrays for the bias, weight, and batch arrays.
 */
public class RandomArrays {
	 /*Create an array where each value will be randomly chosen between
	  * specific values.*/
   public static double[] createRandomArray(int layer, double minimum,
                                            double maximum){
       if(layer < 1){
           return null;
       }
       double[] array = new double[layer];
       for(int i = 0; i < layer; i++){
           array[i] = randomValue(minimum, maximum);
       }
       return array;
   }

   /*By calling the function createRandomArray, it creates a 2D array
    *  that will iterate through each layer, randomly adding value to the 
    *  previous layer.  */
   public static double[][] createRandomArrayWeight(int layer,
                                                    int previousLayer,
                                                    double minimum,
                                                    double maximum){
       if(layer < 1 || previousLayer < 1){
           return null;
       }
       double[][] array = new double[layer][previousLayer];
       for(int i = 0; i < layer; i++){
           array[i] = createRandomArray(previousLayer, minimum,
        		   maximum);
       }
       return array;
   }
   /*This function will generate a random number within a certain range.*/
   public static double randomValue(double minimum, double maximum){
       return Math.random()*(maximum-minimum) + minimum;
   }
   /*This function will return a random batch of values.*/
   public static Integer[] randomValuesBatch(int minimum, int maximum,
                                             int batchSize){
	   minimum --;
       if(batchSize > (maximum-minimum)){
           return null;
       }
       Integer[] batchArray = new Integer[batchSize];
       for(int i = 0; i < batchSize; i++){
           int randomNumber = (int) (Math.random()*
                               (maximum-minimum+1) + minimum);
           batchArray[i] = randomNumber;
       }
       return batchArray;
   }
	
}
