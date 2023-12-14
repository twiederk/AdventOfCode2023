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
        var firstColumn = column(indexLeft)
        for (indexRight in data[0].lastIndex downTo 1) {
            val secondColumn = column(indexRight)
            if (firstColumn == secondColumn) {
                if (indexLeft + 1 == indexRight) {
                    return indexLeft
                }
                indexLeft++
                firstColumn = column(indexLeft)
            }
        }
        return NO_MIRROR
    }

    fun mirrorRight(): Int {
        var indexRight = data[0].lastIndex
        var firstColumn = column(indexRight)
        for (indexLeft in 0..data[0].lastIndex) {
            val secondColumn = column(indexLeft)
            if (firstColumn == secondColumn) {
                if (indexLeft + 1 == indexRight) {
                    return indexLeft
                }
                indexRight--
                firstColumn = column(indexRight)
            }
        }
        return NO_MIRROR
    }

    fun mirrorTop(): Int {
        var indexTop = 0
        var firstRow = data[indexTop]
        for (indexBottom in data.lastIndex downTo 1) {
            val secondRow = data[indexBottom]
            if (firstRow == secondRow) {
                if (indexTop + 1 == indexBottom) {
                    return indexTop
                }
                indexTop++
                firstRow = data[indexTop]
            }
        }
        return NO_MIRROR
    }

    fun mirrorBottom(): Int {
        var indexBottom = data.lastIndex
        var firstRow = data[indexBottom]
        for (indexTop in 0..data.lastIndex) {
            val secondRow = data[indexTop]
            if (firstRow == secondRow) {
                if (indexTop + 1 == indexBottom) {
                    return indexTop
                }
                indexBottom--
                firstRow = data[indexBottom]
            }
        }
        return NO_MIRROR
    }

    fun mirror(): Mirror {
        var index = mirrorLeft()
        if (index != NO_MIRROR) return Mirror(Face.COL, index)
        index = mirrorRight()
        if (index != NO_MIRROR) return Mirror(Face.COL, index)
        index = mirrorTop()
        if (index != NO_MIRROR) return Mirror(Face.ROW, index)
        index = mirrorBottom()
        if (index != NO_MIRROR) return Mirror(Face.ROW, index)
        throw IllegalStateException("No mirror found for $this")
    }


}

data class Mirror(
    val face: Face,
    val index: Int
)

enum class Face {
    COL, ROW
}
