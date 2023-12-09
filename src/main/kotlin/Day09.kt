import java.nio.file.Path

class Day09 {
    fun loadHistories(path: Path): List<List<Int>> {
        val histories = mutableListOf<List<Int>>()
        val listOfStrings = Resources.resourceAsListOfString(path.toFile().name)
        for (string in listOfStrings) {
            val splitted = string.split(" ")
            val history = splitted.map { it.toInt() }
            histories.add(history)
        }
        return histories
    }

}

