import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.name

class Day01 {

    fun loadData(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.name)
    }

    fun readCalibrationValue(line: String): Int {
        val firstDigit = readFirstDigit(line)
        val secondDigit = readLastDigit(line)
        return "$firstDigit$secondDigit".toInt()
    }

    fun readFirstDigit(line: String): Char {
        return line.first { input -> input in '0'..'9' }
    }

    fun readLastDigit(line: String): Char {
        return line.last { input -> input in '0'..'9' }
    }

    fun sumUpCalibrationValues(calibrationValues: List<String>): Int {
        return calibrationValues.sumOf { readCalibrationValue(it) }
    }
}

fun main() {

    val calibrationValues = Day01().loadData(Path("src", "main", "resources", "Day01_InputData.txt"))
    part1(calibrationValues)
}

private fun part1(calibrationValues: List<String>) {
    println("Day01 part1:  ${Day01().sumUpCalibrationValues(calibrationValues)}")
}

private fun part2() {
}
