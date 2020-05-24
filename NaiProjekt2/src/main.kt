fun main() {
    val trainSet = "iris-train.csv"
    val testSet = "iris-test.csv"
    val teachConstant = 3.0

    val getIrisData = GetIrisData.load(trainSet, testSet)
    val irisPerceptron = IrisPerceptron.learn(teachConstant, getIrisData.trainData)

    irisPerceptron.startPerceptronForDataSet(getIrisData.testData, getIrisData.answers)
    irisPerceptron.startPerceptronForUserInput()
}