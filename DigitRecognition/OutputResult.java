package DigitRecognition;

public class OutputResult {
	public static double totalAccuracy;
	public static int resultCounter;
	public static double averageAccuracy;
    /*
     * Function to output the result of the algorithms.
     * */
    public static void outputResults(int correctResult, int total){
        System.out.println("Number of good results: " + correctResult);
        System.out.println("Number of wrong results: " +
                            (total - correctResult));
        System.out.println(correctResult + "/" + total);
        System.out.println("Accuracy: " + ((double) correctResult * 100 /
                                            (double) total) + " %");
        resultCounter++;
        totalAccuracy += (double) correctResult * 100 / (double) total;
        if(resultCounter == 2) {
        	averageAccuracy = totalAccuracy / 2;
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println();
        	System.out.println("Average Accuracy: " + averageAccuracy + " %");
        }
    }
}
