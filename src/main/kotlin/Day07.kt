import java.nio.file.Path
import java.nio.file.Paths

class Day07 {

    fun loadHands(path: Path): List<Hand> {
        val data = Resources.resourceAsListOfString(path.toFile().name)
        return data.map {
            val result = it.split(" ")
            Hand(result[0], result[1].toInt())
        }
    }

    fun orderHands(hands: List<Hand>): List<Hand> {
        return hands.sortedWith(HandComparator())
    }

    fun totalWins(orderedHands: List<Hand>): Long {
        var totalWins = 0L
        for ((index, hand) in orderedHands.withIndex()) {
            totalWins += hand.bid * (index + 1)
        }
        return totalWins
    }

}

data class Hand(
    val cards: String,
    val bid: Int
) {
    fun groupCards(): Map<Char, List<Char>> {
        return cards.groupBy { it }
    }

    fun strength(): Strength {
        val groups = groupCards()
        return when (groups.size) {
            1 -> return Strength.FIVE_OF_A_KIND
            2 -> return if (groups.values.maxOf { it.size } == 4)
                Strength.FOUR_OF_A_KIND
            else
                Strength.FULL_HOUSE

            3 -> return if (groups.values.maxOf { it.size } == 3)
                Strength.THREE_OF_A_KIND
            else
                Strength.TWO_PAIR

            4 -> return Strength.ONE_PAIR
            else -> Strength.HIGH_CARD
        }
    }

    fun strengthWithJoker(): Strength {
        var groups = groupCards()
        val joker = groups['J']
        if (joker != null) {
            val newGroups = groups.filter { it.key != 'J' }.toMutableMap()
            val sortedGroups = newGroups.values.sortedBy { it.size }.reversed()
            val largestGroup = newGroups[sortedGroups.first()[0]]
            val keyOfLargestGroup = largestGroup!![0]
            val newlargestGroup = largestGroup.toMutableList()
            newlargestGroup.addAll(joker)
            val newestGroups = groups.filter { it.key != 'J' && it.key != keyOfLargestGroup }.toMutableMap()
            newestGroups[keyOfLargestGroup] = newlargestGroup.toList()
            groups = newestGroups
        }
        return when (groups.size) {
            1 -> return Strength.FIVE_OF_A_KIND
            2 -> return if (groups.values.maxOf { it.size } == 4)
                Strength.FOUR_OF_A_KIND
            else
                Strength.FULL_HOUSE

            3 -> return if (groups.values.maxOf { it.size } == 3)
                Strength.THREE_OF_A_KIND
            else
                Strength.TWO_PAIR

            4 -> return Strength.ONE_PAIR
            else -> Strength.HIGH_CARD
        }
    }

}

enum class Strength {
    HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND
}

class HandComparator(
    val cardOrder: String = CARD_ORDER_PART_1
) : Comparator<Hand> {

    companion object {
        const val CARD_ORDER_PART_1 = "23456789TJQKA"
        const val CARD_ORDER_PART_2 = "J23456789TQKA"

    }

    override fun compare(hand1: Hand, hand2: Hand): Int {
        val strength1 = hand1.strength().ordinal
        val strength2 = hand2.strength().ordinal
        val typeCompare = strength1.compareTo(strength2)
        if (typeCompare != 0)
            return typeCompare
        for (index in hand1.cards.indices) {
            val order1 = cardOrder.indexOf(hand1.cards[index])
            val order2 = cardOrder.indexOf(hand2.cards[index])
            val orderCompare = order1.compareTo(order2)
            if (orderCompare != 0)
                return orderCompare
        }
        return 0
    }

}

fun main() {
    val day07 = Day07()
    val hands = day07.loadHands(Paths.get("src", "main", "resources", "Day07_InputData.txt"))
    val orderedHands = day07.orderHands(hands)
    val part1 = day07.totalWins(orderedHands)
    println("Day07 part1: $part1")
}