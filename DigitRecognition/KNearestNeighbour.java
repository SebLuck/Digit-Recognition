package DigitRecognition;

		import java.io.FileNotFoundException;
		import java.util.*;

/*
*This is the class for the KNN algorithm. The training and test data will be 
*read from the class ReadFileKNN, which will create objects of the digits. 
*/
public class KNearestNeighbour {
	public int guessCounter;
	/*
		The main method for the KNN algorithm reads in an input file, 
		calculates the euclidean distance between test and train points,
		 and prints out the results.
	 */
	public void KNNMethod(String trainDataFile, String testDataFile)
			throws FileNotFoundException{
		ReadFileKNN readData = new ReadFileKNN();
		List<Digit> trainDigitList = readData.trainData(trainDataFile);
		List<Digit> testDigitList = readData.testData(testDataFile);
		int total = trainDigitList.size();
		//Iterate over the digits in the test csv file. 
		for(Digit testDistance : testDigitList){
			// Output list for one test iteration.
			List<DigitResult> displayList = new ArrayList<>();
			//Loop through the value of each pixel from the test data.
			for(int i = 0; i < testDistance.grayScale.length; i++){
				//Loop through the train csv file's digits. 
				for(Digit trainDistance : trainDigitList){
					double euclideanDistance = euclideanDistance
							(trainDistance.grayScale, testDistance.grayScale,
									trainDistance.grayScale.length);
//Add the Euclidean distance and the train label to displayList.
					displayList.add(new DigitResult(euclideanDistance,
							trainDistance.label));
				}
			}
			//Sort the displayList in ascending order.
			displayList.sort(new compareDistance());
			/* This if statement will check if the prediction is correct or 
			 * not. If the prediction is correct, it will add 1 to the 
			 * variable. This will help to know the number of times the
			 *  program has guessed correctly.*/
			if(Integer.parseInt(testDistance.label) == Integer.parseInt(displayList.get(0).digitLabelResult)){
				guessCounter++;
			}
		}
		// Return the final results.
		OutputResult.outputResults(guessCounter, total);
		guessCounter = 0;
	}
/*This function will find the Euclidean distance.*/
	public double euclideanDistance(Integer[] trainData, Integer[] testData,
									int size){
		double distance = 0.0;
		for(int i = 0; i < size; i++){
			distance += Math.pow(trainData[i] - testData[i], 2);
		}
		return Math.sqrt(distance);
	}
	/*Class that will compare results with distances. */
	static class compareDistance implements Comparator<DigitResult> {
		@Override
		public int compare(DigitResult result1, DigitResult result2){
			return Double.compare(result1.distance, result2.distance);
		}
	}

}
