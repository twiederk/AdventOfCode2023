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

    fun mirrorRow(): Int {
        for (index in 0..data.size - 2) {
            val firstLine = data[index]
            val secondLine = data[index + 1]
            if (firstLine == secondLine) {
                return index + 1
            }
        }
        return NO_MIRROR
    }

    fun mirrorCol(): Int {
        for (index in 0..data[0].length - 2) {
            val firstColumn = column(index)
            val secondColumn = column(index + 1)
            if (firstColumn == secondColumn) {
                return index + 1
            }
        }
        return NO_MIRROR
    }

    private fun column(index: Int): String {
        var column = ""
        for (line in data) {
            column += line[index]
        }
        return column
    }
}