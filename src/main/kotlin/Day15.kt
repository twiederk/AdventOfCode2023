import java.nio.file.Path
import java.nio.file.Paths

class Day15 {

    fun loadInitializationSequence(path: Path): List<String> {
        return Resources.resourceAsText(path.toFile().name).split(',')
    }

    fun calculateHashCode(input: String): Int {
        var currentValue = 0
        for (char in input) {
            currentValue += char.code
            currentValue *= 17
            currentValue %= 256
        }
        return currentValue
    }

    fun part1(initializationSequence: List<String>): Int {
        return initializationSequence.sumOf { calculateHashCode(it) }
    }

}

fun main() {
    val day15 = Day15()
    val initializationSequence =
        day15.loadInitializationSequence(Paths.get("src", "main", "resources", "Day15_InputData.txt"))
    val part1 = day15.part1(initializationSequence)
    println("part1 = $part1")
}
