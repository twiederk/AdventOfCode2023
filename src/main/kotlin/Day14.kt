import java.nio.file.Path

class Day14 {

    fun loadPlatform(path: Path): List<CharArray> {
        return Resources.resourceAsListOfString(path.toFile().name).map { it.toCharArray() }
    }

    fun tilt(platform: List<CharArray>): List<CharArray> {
        val nextPlatform = mutableListOf<CharArray>()
        nextPlatform.add(platform[0])
        for (lineIndex in 1..platform.lastIndex) {
            val line = platform[lineIndex]
            for ((colIndex, char) in line.withIndex()) {
                when (char) {
                    '#', '.' -> continue
                    else -> {
                        val aboveChar = nextPlatform[lineIndex - 1][colIndex]
                        if (aboveChar == '.') {
                            nextPlatform[lineIndex - 1][colIndex] = 'O'
                            line[colIndex] = '.'
                        }
                    }
                }
            }
            nextPlatform.add(line)
        }
        return nextPlatform
    }


}