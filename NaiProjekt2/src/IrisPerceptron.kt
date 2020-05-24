import kotlin.random.Random

class IrisPerceptron {

    private lateinit var trainSet: List<Iris>
    private var learnCalled = false
    private var t: Double = randomT()
    var attributeCount = 0
    private var weights = ArrayList<Double>()

    companion object {

        fun learn(teachConstant: Double, trainSet: List<Iris>): IrisPerceptron {
            val irisPerceptron = IrisPerceptron()
            irisPerceptron.trainSet = trainSet
            irisPerceptron.attributeCount = trainSet[0].attributes.size

            for (i in 0 until irisPerceptron.attributeCount) {
                irisPerceptron.weights.add(irisPerceptron.randomW())
            }

            for (iris in irisPerceptron.trainSet) {

                val d = when (iris.javaClass.toString()) {
                    "class IrisSetosa" -> 1
                    "class IrisVersicolour" -> 0
                    else -> throw Exception("Unrecognized Iris in trainSet")
                }

                var net = 0.0

                for (i in 0 until irisPerceptron.attributeCount) {
                    net += irisPerceptron.weights[i] * iris.attributes[i]
                }

                var y = if (net >= irisPerceptron.t) 1 else 0

                while (d != y) {
                    val m = (d - y) * teachConstant

                    for (i in iris.attributes.indices) {
                        irisPerceptron.weights[i] = irisPerceptron.weights[i] + (m * iris.attributes[i])
                    }

                    net = 0.0
                    for (i in 0 until irisPerceptron.attributeCount) {
                        net += irisPerceptron.weights[i] * iris.attributes[i]
                    }
                    irisPerceptron.t = irisPerceptron.t + (-1 * m)

                    y = if (net >= irisPerceptron.t) 1 else 0
                }
            }

            irisPerceptron.learnCalled = true
            return irisPerceptron
        }
    }

    fun startPerceptronForDataSet(testSet: List<Iris>, answerList: List<String>) {
        if (learnCalled) {
            val list = ArrayList<Iris>()
            println(t)
            println(weights)
            for (iris in testSet) {
                val recognizedIris = recognizeIris(iris)
                list.add(recognizedIris)
                println(recognizedIris)
            }
            println(checkAccuracy(list, answerList))
        } else {
            throw Exception("Perceptron nie odbyl nauki")
        }
    }

    fun startPerceptronForUserInput() {
        if (learnCalled) {
            GUI(this)
        } else {
            throw Exception("Perceptron nie odbyl nauki")
        }
    }

    fun recognizeIris(iris: Iris): Iris {
        val attributes = ArrayList<String>()
        var sum = 0.0
        for (i in 0 until attributeCount) {
            attributes.add(iris.attributes[i].toString())
            sum += weights[i] * iris.attributes[i]
        }
        return if (sum >= t) IrisSetosa(attributes) else IrisVersicolour(attributes)
    }

    private fun checkAccuracy(irisList: List<Iris>, answerList: List<String>): String {
        var g = irisList.size
        for (i in irisList.indices) {
            val irisName =
                when {
                    irisList[i].javaClass.toString() == "class IrisSetosa" -> "Iris-setosa"
                    irisList[i].javaClass.toString() == "class IrisVersicolour" -> "Iris-versicolor"
                    else -> "IrisUnrecognized"
                }
            if (irisName != answerList[i]) g--
        }
        return "Accuracy: $g/${irisList.size}"
    }

    private fun randomT(): Double {
        return Random.nextDouble(-1.0,1.0)
    }

    private fun randomW(): Double {
        return Random.nextDouble(-3.0,3.0)
    }
}