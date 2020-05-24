fun main() {

    val fileName = "testSet.csv"
    val k = 3

    val groups = Utils.createGroups(k)
    val points = Utils.getPoints(fileName).shuffled()
    val pointSize = points[0].coordinates.size

    var groupsWithPoints = Utils.addPointsToGroup(points, groups)
    var cList = Utils.getCList(groupsWithPoints, pointSize)

    for (i in groupsWithPoints) {
        println(i)
    }
    for (i in cList) {
        println(i)
    }
    println(Utils.getEMap(groupsWithPoints, cList))

    println("\n\nK-means:")

    var spr = true
    var counter = 1


    while (spr) {
        println("\nIteration: $counter")
        counter++
        spr = false

        val newGroup = Utils.createGroups(k)
        for (group in groupsWithPoints) {
            val groupName = group.name
            for (point in group.list) {
                val list = ArrayList<Double>()
                for (c in cList) {
                    list.add(Utils.distance(point, c))
                }

                var minId = 0
                for (i in 0..list.size - 2) {
                    if (list[minId] > list[i + 1]) {
                        minId = i + 1
                    }
                }
                val newGroupName = newGroup[minId].name
                newGroup[minId].list.add(point)
                if (groupName != newGroupName) spr = true
            }
        }
        groupsWithPoints = newGroup

        cList = Utils.getCList(groupsWithPoints, pointSize)
        val e = Utils.getEMap(groupsWithPoints, cList)
        for (group in groupsWithPoints) {
            println(group)
        }
        for (group in cList) {
            println(group)
        }
        println(e)
    }

}