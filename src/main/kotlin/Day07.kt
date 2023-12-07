import java.nio.file.Path

class Day07 {

    fun loadHands(path: Path): List<Hand> {
        val data = Resources.resourceAsListOfString(path.toFile().name)
        return data.map {
            val result = it.split(" ")
            Hand(result[0], result[1].toInt())
        }
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
        if (groups.size == 1) {
            return Strength.FIVE_OF_A_KIND
        }
        if (groups.size == 2) {
            if (groups.values.maxOf { it.size } == 4)
                return Strength.FOUR_OF_A_KIND
            else
                return Strength.FULL_HOUSE
        }
        if (groups.size == 3) {
            if (groups.values.maxOf { it.size } == 3)
                return Strength.THREE_OF_A_KIND
            else
                return Strength.TWO_PAIR
        }
        if (groups.size == 4) {
            return Strength.ONE_PAIR
        }
        return Strength.HIGH_CARD
    }
}

enum class Strength {
    HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND
}
