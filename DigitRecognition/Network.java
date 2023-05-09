package DigitRecognition;


/*Class for the neural network algorithm where the network will be built and 
 * where it will process the gradient descent algorithm.*/
public class Network {
    /* The array will have two indices for the layer and the neuron to store 
     * the output of each neuron. */
    public double[][] outputArray;
    /*This array has three indices: layer, neuron, and neuron in the previous 
     * layer. Every neuron has multiple weights connected to the previous layer
     *  but only one output.*/
    public double[][][] weightArray;
    
    public double[][] biasArray;

    public double[][] errorArray;
    /* The derivative of the activation function with respect to the output
    * of a single neuron*/
    public double[][] derivativeActivationArray;

    /* This array will store the number of neurons in the input layer, 
     * hidden layer, and output layer.*/
    public int[] networkLayer;
    // Number of neurons in the input layer.
    public int inputSize;
    //Number of neurons in the output layer.
    public int outputSize;
    //Number of neurons in the hidden layer.
    public int hiddenSize;
    public int networkLayerSize;

    public Network(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
        this.networkLayerSize = 3;
        this.networkLayer = new int [networkLayerSize];
        this.networkLayer[0] = inputSize;
        this.networkLayer[1] = hiddenSize;
        this.networkLayer[2] = outputSize;
        this.outputArray = new double[networkLayerSize][];
        this.weightArray = new double[networkLayerSize][][];
        this.biasArray = new double[networkLayerSize][];
        this.errorArray = new double[networkLayerSize][];
        this.derivativeActivationArray = new double[networkLayerSize][];
        /*This for loop will initialise the arrays.*/
        for(int i = 0; i < networkLayerSize; i++){
            this.outputArray[i] = new double[networkLayer[i]];
            this.errorArray[i] = new double[networkLayer[i]];
            this.derivativeActivationArray[i] = new double[networkLayer[i]];
            // The biases are initialised between -1 and 1.
            this.biasArray[i] = RandomArrays.createRandomArray
            		(networkLayer[i], -1, 1);
            /*This will create a weights array for every layer except the 
             * first, as it is not connected to a previous layer. The weights
             *  are set between -1 and 1. */
            if(i > 0){
                weightArray[i] = RandomArrays.createRandomArrayWeight
                		(networkLayer[i], networkLayer[i-1], -1, 1);
            }
        }
    }

    /*This function will train the data set based on the hyperparameters. 
     * It will use the MiniBatch gradient descent algorithms. It will choose a
     * certain number of digits from the data set to train on. This 
     *function will also call two different functions to train the network.*/
    public void GradientDescent(TrainingDataSet data, int epoch,
                                          int loops, int batchSize,
                                          double learnRate){
        for(int i = 0; i < epoch; i++){
            for(int j = 0; j < loops; j++){
                TrainingDataSet batch = data.batchExtract(batchSize);
                for(int k = 0; k < batchSize; k++){
                    feedForward(batch.getInput(k));
                    backPropagation(batch.getOutput(k), learnRate);
                }
            }
        }
    }


    /*This function will perform the feed-forward process, calculating the 
     * sigmoid from the activation function and returning the output layer. */
   public double[] feedForward(double[] inputArray){

       // Add the input value to the first index of outputArray.
       outputArray[0] = inputArray;
       /* Iterate through the hidden layer and output layer. To make sure
        * that the for loop does not iterate through the first layer, i is set
        *  to 1. This for loop will calculate the output of every layer.*/
       for(int layer = 1; layer < networkLayerSize; layer++){
           findActivationFunction(layer);
       }
       return outputArray[networkLayerSize - 1];
   }
   /*This function will find the activation function using the feed
   * forward method. 
   * The output of the current layer at the specific neuron is equal to the
   *  activation function. The activation function is a sigmoid function.*/
   public void findActivationFunction(int layer){
       // For loop to iterate through every neuron in the specific layer.
       for(int i = 0; i < networkLayer[layer]; i++){
           double sumInput = 0;
           // Iterate through every neuron in the previous layer.
           for(int j = 0; j < networkLayer[layer-1]; j++){
/*The sum will be increased by the output of that neuron from the previous 
 * layer, and it  will be multiplied by the weight that connects the previous
 *  layer with the neuron. */
               sumInput += outputArray[layer-1][j] * weightArray[layer][i][j];
           }
//Add the bias of the current layer at the specific neuron to the sum.
           sumInput = sumInput + biasArray[layer][i];
           outputArray[layer][i] =  (double)1 / (1 + Math.exp(-sumInput));
       }
   }
   
   /* This function will apply the backpropagation algorithm to update
    * the weights and biases.*/
   public void backPropagation(double[] labelArray, double learningRate){
       findDerivativeActivation();
       // Loop through all the output neuron to find the error.
       for(int i = 0; i < networkLayer[networkLayerSize -1]; i++ ){
           errorArray[networkLayerSize -1][i] =
                   (outputArray[networkLayerSize - 1][i] - labelArray[i])
                           * derivativeActivationArray
                           [networkLayerSize -1][i];
       }
       //Start in the hidden layer and go into every neuron in that layer.
       for(int layer = networkLayerSize-2; layer > 0 ; layer--) {
           for (int i = 0; i < networkLayer[layer]; i++) {
               double sumInput = 0;
//Go to every neuron in the next layer where the error was already calculated.
               for (int j = 0; j < networkLayer[layer+1]; j++) {
                   sumInput += weightArray[layer+1][j][i] * 
                		   errorArray[layer+1][j];
               }
               errorArray[layer][i] = sumInput * 
            		   derivativeActivationArray[layer][i];
           }
       }
       /* This will update the bias and weight of the network based on one 
        * iteration of the gradient descent.*/
       for(int layer = 1; layer < networkLayerSize; layer++){
           updateWeightBias(learningRate, layer);
       }

   }
   
   /*This function will find the derivative of the activation function.*/
   public void findDerivativeActivation(){
       for(int i = 1; i < networkLayerSize; i++){
           for(int j = 0; j < networkLayer[i]; j++){
               derivativeActivationArray[i][j] = outputArray[i][j] *
                       (1 - outputArray[i][j]);
           }
       }
   }


   /* For each weight that connects a neuron to another. The output is going
    *  to be multiplied by the learning rate and error. Then, it will be added
    *   to the current weight.*/
   public void updateWeightBias(double learningRate,int layer){
       //Go into every neuron in that layer.
       for(int i = 0; i < networkLayer[layer]; i++){
           /*Go into every neuron in the previous layer to get each weight. 
            * Iterate through every weight that connects the current neuron
            *  to the previous neurons.*/
           for(int j = 0; j < networkLayer[layer -1]; j++){
               double deltaWeight = -learningRate * outputArray[layer -1][j]
                       * errorArray[layer][i];
                   /*The weight in the current layer of the current neuron
                    connected to the previous neuron.*/
               weightArray[layer][i][j] += deltaWeight;
           }
           //The bias is not connected with the previous neuron.
           double deltaBias =  -learningRate * errorArray[layer][i];
           biasArray[layer][i] += deltaBias;
       }
   }

}
