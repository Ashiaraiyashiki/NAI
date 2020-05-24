import java.io.File
import kotlin.collections.ArrayList

class GetIrisData {

    lateinit var trainData: List<Iris>
    lateinit var testData: List<Iris>
    lateinit var answers: List<String>

    companion object {
        fun load(trainData: String, testData: String): GetIrisData {
            val downloadData = GetIrisData()

            downloadData.trainData = downloadData.readTrainFile(trainData)
            downloadData.testData = downloadData.readTestFile(testData)
            downloadData.answers = downloadData.loadAnswers(testData)

            return downloadData
        }
    }

    private fun readTrainFile(path: String): List<Iris> {
        val list = ArrayList<Iris>()
        File(path).forEachLine {
            val splitString = it.split(";")
            when (splitString[splitString.size - 1]) {
                "Iris-setosa" -> {
                    list.add(IrisSetosa(splitString.subList(0, splitString.size-1)))
                }
                "Iris-versicolor" -> {
                    list.add(IrisVersicolour(splitString.subList(0, splitString.size-1)))
                }
            }
        }
        return list
    }

    private fun readTestFile(path: String): List<Iris> {
        val list = ArrayList<Iris>()
        File(path).forEachLine {
            val splitString = it.split(";")
            list.add(IrisUnrecognized(splitString.subList(0, splitString.size-1)))
        }
        return list
    }

    private fun loadAnswers(path: String): List<String> {
        val list = ArrayList<String>()
        File(path).forEachLine {
            val splitString = it.split(";")
            list.add(splitString[splitString.size - 1])
        }
        return list
    }
}