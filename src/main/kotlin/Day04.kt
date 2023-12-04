import java.lang.IllegalArgumentException
import java.nio.file.Path

class Day04 {

    fun loadData(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun createCard(line: String): Card {
        val winningNumbersString = line.substringAfter(": ").substringBefore(" | ").split(' ')
        val winningNumbersInt = winningNumbersString.filter { it.isNotEmpty() }. map { it.toInt() }

        val numbersString = line.substringAfter(" | ").split(' ')
        val numbersInt = numbersString.filter { it.isNotEmpty() }. map { it.toInt() }
        return Card(winningNumbersInt, numbersInt)
    }

}


data class Card(
    val winningNumbers: List<Int>,
    val numbers: List<Int>
) {
    fun wins(): List<Int> {
        return numbers.filter { winningNumbers.contains(it) }
    }

    fun points(): Int =
        when (wins().size) {
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 4
            4 -> 8
            5 -> 16
            6 -> 32
            7 -> 64
            8 -> 128
            9 -> 256
            10 -> 512
            else -> throw IllegalArgumentException("Number of wins to large")
        }

}