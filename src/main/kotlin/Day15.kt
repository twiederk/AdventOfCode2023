import java.nio.file.Path
import java.nio.file.Paths

class Day15 {

    val boxes = Array(256) { Box(it) }

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

    fun execute(instruction: Instruction) {
        val boxIndex = calculateHashCode(instruction.label)
        boxes[boxIndex].addOrUpdate(Lens(instruction.label, instruction.focalLength))
    }

}

data class Instruction(
    val label: String,
    val operation: Char,
    val focalLength: Int = 0
)

data class Box(
    val number: Int
) {
    val lenses = mutableListOf<Lens>()

    fun addOrUpdate(lens: Lens) {
        val existingLens = lenses.find { it.label == lens.label }
        if (existingLens == null) {
            lenses.add(lens)
        } else {
            existingLens.focalLength = lens.focalLength
        }
    }

}

data class Lens(
    val label: String,
) {
    var focalLength: Int = 0

    constructor(label: String, focalLength: Int) : this(label) {
        this.focalLength = focalLength
    }

}


fun main() {
    val day15 = Day15()
    val initializationSequence =
        day15.loadInitializationSequence(Paths.get("src", "main", "resources", "Day15_InputData.txt"))
    val part1 = day15.part1(initializationSequence)
    println("part1 = $part1")
}
