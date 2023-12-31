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

    fun wonCards(cardRepository: List<Card>): List<Card> {
        val wonCards = mutableListOf<Card>()
        cardRepository.forEach {  it.wonCards(cardRepository, wonCards) }
        return wonCards
    }

    fun totalCards(cardRepository: List<Card>): Int {
        val wonCards = wonCards(cardRepository)
        return cardRepository.size + wonCards.size
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

    fun wonCards(cardRepository: List<Card>, wonCards: MutableList<Card>) {
        for (index in number ..< number + wins().size) {
            if (index >= cardRepository.size)
                continue
            val wonCard = cardRepository[index].copy()
            wonCards.add(wonCard)
            wonCard.wonCards(cardRepository, wonCards)
        }
    }

}

fun main() {
    val day04 = Day04()
    val data = day04.loadData(Paths.get("src", "main", "resources", "Day04_InputData.txt"))
    val cards = day04.createAllCards(data)

    val totalPoints = day04.totalPoints(cards)
    println("Day04 part1: $totalPoints")

    val totalCards = day04.totalCards(cards)
    println("Day04 part2: $totalCards")
}