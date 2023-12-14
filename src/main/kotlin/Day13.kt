import java.nio.file.Path

class Day13 {
    fun loadPatterns(path: Path): List<Pattern> {
        val rawData = Resources.resourceAsListOfString(path.toFile().name)

        val patterns = mutableListOf<Pattern>()
        var data = mutableListOf<String>()
        for (line in rawData) {
            if (line.isNotEmpty()) {
                data.add(line)
            } else {
                patterns.add(Pattern(data))
                data = mutableListOf()
            }
        }
        patterns.add(Pattern(data))
        return patterns
    }

}

data class Pattern(
    val data: List<String>
) {

    companion object {
        const val NO_MIRROR = -1
    }

    private fun column(index: Int): String {
        var column = ""
        for (line in data) {
            column += line[index]
        }
        return column
    }

    fun mirrorLeft(): Int {
        var indexLeft = 0
        val firstColumn = column(indexLeft)
        for (indexRight in data.lastIndex downTo 1) {
            val secondColumn = column(indexRight)
            if (firstColumn == secondColumn) {
                if (indexLeft + 1 == indexRight) {
                    return indexLeft
                }
                indexLeft++
            }
        }
        return NO_MIRROR
    }


}