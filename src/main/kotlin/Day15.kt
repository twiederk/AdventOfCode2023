import java.nio.file.Path
import java.nio.file.Paths

class Day15 {

    val boxes = Array(256) { Box(it + 1) }

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
        return input.map { parseInstruction(it) }
    }

    fun parseInstruction(data: String): Instruction =
        if (data.contains("-")) {
            val label = data.substringBefore('-')
            Instruction(label, '-')
        } else {
            val label = data.substringBefore('=')
            val focalLength = data.substringAfter('=').toInt()
            Instruction(label, '=', focalLength)
        }

    fun execute(instruction: Instruction) {
        val boxIndex = calculateHashCode(instruction.label)
        if (instruction.operation == '=') {
            boxes[boxIndex].addOrUpdate(Lens(instruction.label, instruction.focalLength))
        } else {
            boxes[boxIndex].remove(Lens(instruction.label))
        }
    }

    fun executeAll(instructions: List<Instruction>) {
        instructions.forEach { execute(it) }
    }

    fun sumFocusingPower(): Int {
        return boxes.sumOf { it.focusingPower() }
    }

    fun part2(instructions: List<Instruction>): Int {
        instructions.forEach { execute(it) }
        return sumFocusingPower()
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

    fun remove(lens: Lens) {
        lenses.remove(lens)
    }

    fun focusingPower(): Int {
        var focusingPower = 0
        for ((index, lens) in lenses.withIndex()) {
            focusingPower += number * (index + 1) * lens.focalLength
        }
        return focusingPower
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

    val instructions = day15.loadInstructions(Paths.get("src", "main", "resources", "Day15_InputData.txt"))
    val part2 = day15.part2(instructions)
    println("part2 = $part2")
}
