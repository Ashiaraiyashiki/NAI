import java.io.File

class ReadFiles {

    companion object{
        fun readCsvFile(path: String): ArrayList<ArrayList<String>>{
            val list: ArrayList<ArrayList<String>> = ArrayList()
            var counter = 0
            File(path).forEachLine {
                list.add(ArrayList())
                val splitLine = it.split(",")
                for(i in splitLine){
                    list[counter].add(i)
                }
                counter++
            }
            return list
        }
    }

}