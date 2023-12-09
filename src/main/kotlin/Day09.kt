import java.nio.file.Path

class Day09 {
    fun loadHistories(path: Path): List<List<Int>> {
        val linesOfHistories = Resources.resourceAsListOfString(path.toFile().name)
        return linesOfHistories.map { historyLine ->
            historyLine.split(" ").map { historyValue -> historyValue.toInt() }
        }
    }

}

