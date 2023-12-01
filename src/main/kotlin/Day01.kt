import java.nio.file.Path
import javax.naming.NameNotFoundException
import kotlin.io.path.Path
import kotlin.io.path.name

class Day01 {

    companion object {
        val words = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        )

        val wordToDigit = mapOf(
            "one" to '1',
            "two" to '2',
            "three" to '3',
            "four" to '4',
            "five" to '5',
            "six" to '6',
            "seven" to '7',
            "eight" to '8',
            "nine" to '9',
            "1" to '1',
            "2" to '2',
            "3" to '3',
            "4" to '4',
            "5" to '5',
            "6" to '6',
            "7" to '7',
            "8" to '8',
            "9" to '9',
        )
    }

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

    fun readFirstWord(line: String): Char? {
        val result = line.findAnyOf(words)
        result?.let {
            return convertWordToDigit(result.second)
        }
        return null
    }

    fun readLastWord(line: String): Char? {
        val result = line.findLastAnyOf(words)
        result?.let {
            return convertWordToDigit(result.second)
        }
        return null
    }

    fun convertWordToDigit(word: String): Char {
        return wordToDigit.getOrElse(word) { throw NameNotFoundException(word) }
    }

    fun readCalibrationValueWithWords(line: String): Int {
        val firstDigit = readFirstWord(line)
        val secondDigit = readLastWord(line)
        return "$firstDigit$secondDigit".toInt()
    }

    fun sumUpCalibrationValuesWithWords(calibrationValues: List<String>): Int {
        return calibrationValues.sumOf { readCalibrationValueWithWords(it) }
    }

}

fun main() {

    val calibrationValues = Day01().loadData(Path("src", "main", "resources", "Day01_InputData.txt"))
    part1(calibrationValues)
    part2(calibrationValues)
}

private fun part1(calibrationValues: List<String>) {
    println("Day01 part1:  ${Day01().sumUpCalibrationValues(calibrationValues)}")
}

private fun part2(calibrationValues: List<String>) {
    println("Day01 part2:  ${Day01().sumUpCalibrationValuesWithWords(calibrationValues)}")
}
