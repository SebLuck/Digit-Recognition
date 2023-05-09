package DigitRecognition;
/*
 * This class is an object that stores the distance result. 
 */

public class DigitResult {
	// The result of the distance.
	double distance;
	// The label of the digit.
	String digitLabelResult;

	public DigitResult(double distance, String label){
		this.digitLabelResult = label;
		this.distance = distance;
	}
}