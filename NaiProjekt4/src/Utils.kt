import java.io.File
import kotlin.math.pow

class Group(val name: String, val list: ArrayList<Point>) {
    override fun toString(): String {
        return "Group(name='$name', $list)"
    }
}

class Point(private val name: String, val coordinates: ArrayList<Double>) {
    override fun toString(): String {
        return "Point(name='$name', coordinates=$coordinates)"
    }
}

class Utils {

    companion object {

        fun createGroups(k: Int): List<Group> {
            val groups = ArrayList<Group>()
            for (i in 'A'..(64 + k).toChar()) {
                groups.add(Group(i.toString(), ArrayList()))
            }
            return groups
        }

        fun getPoints(fileName: String): List<Point> {
            val list = ArrayList<Point>()
            var tmp = 1
            File(fileName).forEachLine {
                val sLine = it.split(";")
                val coordinatesList = ArrayList<Double>()
                for (i in sLine.indices) {
                    coordinatesList.add(sLine[i].toDouble())
                }
                list.add(Point("P$tmp", coordinatesList))
                tmp++
            }
            return list
        }

        fun addPointsToGroup(points: List<Point>, groups: List<Group>): List<Group> {
            var groupId = 0
            for (point in points) {
                groupId = if (groupId > groups.size - 1) 0 else groupId
                groups[groupId].list.add(point)
                groupId++
            }
            return groups
        }

        fun getCList(groups: List<Group>, size: Int): List<Point> {
            val cList = ArrayList<Point>()
            for (groupId in groups.indices) {
                cList.add(Point("C${groups[groupId].name}", ArrayList()))
                val tmp = size - 1
                for (i in 0..tmp) {
                    var sum = 0.0
                    for (point in groups[groupId].list) {
                        sum += point.coordinates[i]
                    }
                    cList[groupId].coordinates.add(sum / groups[groupId].list.size.toDouble())
                }
            }
            return cList
        }

        fun distance(point: Point, c: Point): Double {
            var sum = 0.0
            for (i in point.coordinates.indices) {
                sum += (point.coordinates[i] - c.coordinates[i]).pow(2.0)
            }
            return sum
        }

        fun getEMap(groups: List<Group>, CList: List<Point>): HashMap<String, Double> {
            val e = HashMap<String, Double>()

            for (i in groups.indices) {
                var sum = 0.0
                for (point in groups[i].list) {
                    sum += distance(point, CList[i])
                }
                e["E${groups[i].name}"] = sum
            }
            return e
        }

    }

}