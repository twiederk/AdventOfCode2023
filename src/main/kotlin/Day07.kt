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
}
