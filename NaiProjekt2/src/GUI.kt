import java.awt.GridLayout
import javax.swing.*

class GUI(private val perceptron: IrisPerceptron): JFrame() {

    init{
        createUI()
    }

    private fun createUI() {
        createGUI()
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 150)
        setLocationRelativeTo(null)
        isVisible = true
    }

    private fun createGUI() {
        val result = JLabel("Result: ")
        val getResult = JButton("getResult")

        val container = JPanel()
        val textFields = JPanel()
        val buttons = JPanel()
        val results = JPanel()

        val attributeFields = ArrayList<JTextField>()
        for(i in 0 until perceptron.attributeCount){
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

                    result.text = "Result: " + perceptron.recognizeIris(IrisUnrecognized(attributes)).javaClass
                } else {
                    throw java.lang.Exception()
                }
            } catch (error: java.lang.Exception) {
                result.text = "Result: Wrong value: " + error.message
            }
        }
    }
}