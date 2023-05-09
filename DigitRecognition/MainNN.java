package DigitRecognition;
/*
 * This is the main class that will run the
 * Neural Network Algorithm.
 */


public class MainNN {
    public static String dataSet1 = "src/DigitRecognition/cw2DataSet1.csv";
    public static String dataSet2 = "src/DigitRecognition/cw2DataSet2.csv";
    public static int inputNeuron = 64;
    public static int hiddenNeuron = 92;
    public static int outputNeuron = 10;
    public static int epoch = 100;
    public static int loops = 281;
    public static int batchSize = 10;
    public static double learningRate = 1.07;
    
    public static void main(String[] args) {
        System.out.println("Neural Network algorithm is running...");
        /* This class generates a neural network with 64 inputs, one
         *  hidden layer with 92 neurons, and ten outputs. */
        Network network1 = new Network(inputNeuron, hiddenNeuron, 
        		outputNeuron);
        //Train data with dataset1.
        TrainingDataSet train1 = ReadFileNN.createTrainSet(dataSet1);
        // set training parameters to train the data.
        network1.GradientDescent(train1, epoch, loops, batchSize, 
        		learningRate);
        TrainingDataSet test1 = ReadFileNN.createTestSet(dataSet2);
        //Get the result of the algorithm by testing the training set.
        System.out.println("-----------------------------");
        System.out.println("Training dataset 1 and testing dataset 2");
        System.out.println();
        TestingDataSet.testTrainingSet(network1, test1);
        System.out.println();
        System.out.println("-----------------------------");

        //Train data with dataset2.
        Network network2 = new Network(inputNeuron, hiddenNeuron,
        		outputNeuron);
        TrainingDataSet train2 = ReadFileNN.createTrainSet(dataSet2);
        network2.GradientDescent(train2, epoch, loops, batchSize, 
        		learningRate);
        TrainingDataSet test2 = ReadFileNN.createTestSet(dataSet1);
        System.out.println("Training dataset 2 and testing dataset 1");
        System.out.println();
        TestingDataSet.testTrainingSet(network2, test2);
        System.out.println();
        System.out.println("-----------------------------");


    }
}