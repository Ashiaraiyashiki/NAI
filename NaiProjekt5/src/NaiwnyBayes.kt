import java.awt.GridLayout
import javax.swing.*

class NaiwnyBayes(val trainSet: ArrayList<ArrayList<String>>) {

    fun start(testSet: ArrayList<ArrayList<String>>) {
        for (testArray in testSet) {
            val result = startForUserValues(testArray)
            println("dla $testArray decyzja to: $result")
        }
    }

    fun startForUserValues(testArray: ArrayList<String>): String{
        val decisions = getAllDecisions()
        val results = ArrayList<Double>()
        for(decision in decisions){
            results.add(bayes(decision, testArray))
        }

        var biggest = 0
        for(i in 1 until results.size){
            if(results[biggest] < results[i]) biggest = i
        }

        return decisions[biggest]
    }

    private fun getAllDecisions(): ArrayList<String>{
        val list: ArrayList<String> = ArrayList()
        for(array in trainSet){
            val decision = array[array.size-1]
            if(!list.contains(decision)) list.add(decision)
        }
        return list
    }

    private fun bayes(decision: String, testArray: ArrayList<String>): Double {
        val size = trainSet[0].size
        val dec = count(decision, size - 1, trainSet)
        val allDec = trainSet.size

        var result = dec.toDouble() / allDec.toDouble()

        for (i in 0..trainSet[0].size -2) {
            var tmp = 0.0
            var count = 0
            val all = countVarious(i, trainSet)
            for (j in trainSet.indices) {
                if (trainSet[j][size - 1] == decision) {
                    if (trainSet[j][i] == testArray[i]) count++
                }
                tmp = (count.toDouble() + 1.0) / (dec.toDouble() + all.toDouble())
            }
            result *= tmp
        }
        return result
    }

    private fun count(cellName: String, col: Int, set: ArrayList<ArrayList<String>>): Int {
        var count = 0
        for (array in set) {
            if (array[col] == cellName) count++
        }
        return count
    }

    private fun countVarious(col: Int, set: ArrayList<ArrayList<String>>): Int {
        var count = 0
        val map = HashMap<String, Int>()

        for (array in set) {
            if (!map.containsKey(array[col])) {
                map[array[col]] = 0
                count++
            }
        }

        return count
    }

    fun gui(){
        BayesGUI(this)
    }

}

private class BayesGUI(val naiwnyBayes: NaiwnyBayes): JFrame(){

    init {
        createUI()
    }

    fun createUI(){

        val result = JLabel("Result: ")
        val getResult = JButton("getResult")

        val container = JPanel()
        val textFields = JPanel()
        val buttons = JPanel()
        val results = JPanel()

        val attributeFields = ArrayList<JTextField>()
        for(i in 0 until naiwnyBayes.trainSet[0].size-1){
            attributeFields.add(JTextField())
            textFields.add(attributeFields[i])
        }

        textFields.layout = GridLayout()
        buttons.layout = GridLayout()
        results.layout = GridLayout()

        buttons.add(getResult)
        results.add(result)
        container.add(textFields)
        container.add(buttons)
        container.add(results)
        container.layout = GridLayout(4, 1)
        container.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        add(container)

        getResult.addActionListener {
            try {
                var isNotEmpty = true
                for(i in attributeFields){
                    if(i.text.isEmpty()) isNotEmpty = false
                }

                if (isNotEmpty) {

                    val attributes = ArrayList<String>()
                    for(i in attributeFields){
                        attributes.add(i.text)
                    }

                    result.text = "Result: " + naiwnyBayes.startForUserValues(attributes)
                } else {
                    throw java.lang.Exception()
                }
            } catch (error: java.lang.Exception) {
                result.text = "Result: Wrong value: " + error.message
            }
        }

        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 150)
        setLocationRelativeTo(null)
        isVisible = true
    }

}