import java.nio.file.Path
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
}

fun main() {

}

private fun part1() {
}

private fun part2() {
}
