fun main() {

    println("Naiwny Bayes")

    val trainSet = ReadFiles.readCsvFile("trainingset.csv")
    val testSet = ReadFiles.readCsvFile("testset.csv")

    println("trainSet : \n $trainSet")
    println("testSet : \n $testSet")

    val bayes = NaiwnyBayes(trainSet)
        bayes.start(testSet)
        bayes.gui()

}