import java.lang.IllegalArgumentException
import java.nio.file.Path
import java.nio.file.Paths

class Day04 {

    fun loadData(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun createCard(line: String): Card {
        val number = line.substringAfter("Card ").substringBefore(":").trim().toInt()
        val winningNumbersString = line.substringAfter(": ").substringBefore(" | ").split(' ')
        val winningNumbersInt = winningNumbersString.filter { it.isNotEmpty() }. map { it.toInt() }

        val numbersString = line.substringAfter(" | ").split(' ')
        val numbersInt = numbersString.filter { it.isNotEmpty() }. map { it.toInt() }
        return Card(number, winningNumbersInt, numbersInt)
    }

    fun createAllCards(data: List<String>): List<Card> {
        return data.map { createCard(it) }
    }

    fun totalPoints(cards: List<Card>): Int {
        return cards.sumOf { it.points()
        }
    }


}


data class Card(
    val number : Int,
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

fun main() {
    val day04 = Day04()
    val data = day04.loadData(Paths.get("src", "main", "resources", "Day04_InputData.txt"))
    val cards = day04.createAllCards(data)

    val totalPoints = day04.totalPoints(cards)

    println("Day04 part1: $totalPoints")
}