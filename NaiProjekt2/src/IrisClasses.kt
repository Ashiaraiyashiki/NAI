interface Iris {
    val attributes: List<Double>
}

open class IrisUnrecognized(attr: List<String>) : Iris {

    final override val attributes = ArrayList<Double>()

    init {
        for (i in attr) {
            attributes.add(i.fullTrim().toDouble())
        }
    }

    override fun toString(): String {
        var result = "( "
        for(i in attributes){
            result+="$i "
        }

        return "${this.javaClass}$result)"
    }
}

class IrisVersicolour(attr: List<String>) : IrisUnrecognized(attr)
class IrisSetosa(attr: List<String>) : IrisUnrecognized(attr)

private fun String.fullTrim() = trim().replace("\uFEFF", "")