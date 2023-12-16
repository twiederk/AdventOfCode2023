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

    fun loadInstructions(path: Path): List<Instruction> {
        val input = loadInitializationSequence(path)
        return input.map {
            if (it.length == 3) {
                Instruction(it.substring(0..1), it[2])
            } else {
                Instruction(it.substring(0..1), it[2], it.substring(3).toInt())
            }
        }
    }

}

data class Instruction(
    val label: String,
    val operation: Char,
    val focalLength: Int = 0
)

fun main() {
    val day15 = Day15()
    val initializationSequence =
        day15.loadInitializationSequence(Paths.get("src", "main", "resources", "Day15_InputData.txt"))
    val part1 = day15.part1(initializationSequence)
    println("part1 = $part1")
}
