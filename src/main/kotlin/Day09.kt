import java.nio.file.Path

class Day09 {
    fun loadHistories(path: Path): List<List<Int>> {
        val linesOfHistories = Resources.resourceAsListOfString(path.toFile().name)
        return linesOfHistories.map { historyLine ->
            historyLine.split(" ").map { historyValue -> historyValue.toInt() }
        }
    }

    fun predictNextValue(history: List<Long>): Long {
        if (history.sum() == 0L) return 0L
        val nextDiff = history.windowed(2).map { it[1] - it[0] }
        return history.last() + predictNextValue(nextDiff)
    }

    fun sumOfNextValues(histories: List<List<Long>>): Long {
        return histories.sumOf { predictNextValue(it) }
    }

}

