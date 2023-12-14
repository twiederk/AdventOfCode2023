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
            if (firstColumn == secondColumn && index == data[0].length / 2) {
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

    fun mirror(): Int {
        val value = mirrorCol()
        if (value != NO_MIRROR) return value
        return mirrorRow() * 100
    }

    private fun printMirrorRow(): List<String> {
        val output = mutableListOf<String>()
        val mirror = mirror()
        for ((index, line) in data.withIndex()) {
            when (index) {
                mirror -> output.add("${index + 1}v${line}v${index + 1}")
                mirror + 1 -> output.add("${index + 1}^${line}^${index + 1}")
                else -> output.add("${index + 1} $line ${index + 1}")
            }
        }
        return output
    }

    fun print(): List<String> {
        if (mirror() < 100) {
            return printMirrorCol()
        }
        return printMirrorRow()
    }

    private fun printMirrorCol(): List<String> {
        val mirrorLine = printMirrorLine()
        val output = mutableListOf<String>()
        val axis = printAxis()
        output.add(axis)
        output.add(mirrorLine)
        data.forEach { output.add(it) }
        output.add(mirrorLine)
        output.add(axis)
        return output
    }

    private fun printMirrorLine(): String {
        val mirror = mirror() - 1
        var mirrorLine = ""
        for (index in data[0].indices) {
            mirrorLine += when (index) {
                mirror -> '>'
                mirror + 1 -> '<'
                else -> ' '
            }
        }
        return mirrorLine
    }

    private fun printAxis(): String {
        var axis = ""
        for (number in 1..data[0].length) {
            axis += "${number % 10}"
        }
        return axis
    }


}