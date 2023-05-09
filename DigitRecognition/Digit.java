package DigitRecognition;
/*
 * The Digit class will represent one digit.
 */
import java.util.ArrayList;

public class Digit {
	// Array that stores the gray scale of the image.
	Integer[] grayScale;
	// Digit represented by the class.
	String label;

	//Constructor.
	public Digit(ArrayList<Integer> input, int label){
		this.label = Integer.toString(label);
		this.grayScale = input.toArray(new Integer[0]);
	}
}