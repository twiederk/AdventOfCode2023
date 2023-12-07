import java.nio.file.Path

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

    fun totalWins(orderedHands: List<Hand>): Int {
        var totalWins = 0
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
}

enum class Strength {
    HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND
}

class HandComparator : Comparator<Hand> {

    companion object {
        const val CARD_ORDER = "23456879TJQKA"

    }
    override fun compare(hand1: Hand, hand2: Hand): Int {
        val strength1 = hand1.strength().ordinal
        val strength2 = hand2.strength().ordinal
        val typeCompare = strength1.compareTo(strength2)
        if (typeCompare != 0)
            return typeCompare
        for (index in hand1.cards.indices) {
            val order1 = CARD_ORDER.indexOf(hand1.cards[index])
            val order2 = CARD_ORDER.indexOf(hand2.cards[index])
            val orderCompare = order1.compareTo(order2)
            if (orderCompare != 0)
                return orderCompare
        }
        return 0
    }

}
