import java.nio.file.Path
import java.nio.file.Paths

class Day09 {

    fun loadHistories(path: Path): List<List<Int>> {
        val linesOfHistories = Resources.resourceAsListOfString(path.toFile().name)
        return linesOfHistories.map { line -> line.split(" ").map { value -> value.toInt() } }
    }

    fun predictNextValue(history: List<Int>): Int {
        if (history.all { it == 0 }) return 0
        val nextDiff = history.windowed(2).map { it[1] - it[0] }
        return history.last() + predictNextValue(nextDiff)
    }

    fun sumOfNextValues(histories: List<List<Int>>): Int {
        return histories.sumOf { predictNextValue(it) }
    }

}

fun main() {
    val day09 = Day09()
    val histories = day09.loadHistories(Paths.get("src", "main", "resources", "Day09_InputData.txt"))
    val part1 = day09.sumOfNextValues(histories)
    println("Day09 part1: $part1")
}