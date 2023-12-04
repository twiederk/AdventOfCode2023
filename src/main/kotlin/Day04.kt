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
)