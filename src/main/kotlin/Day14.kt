import java.nio.file.Path

class Day14 {

    fun loadPlatform(path: Path): List<CharArray> {
        return Resources.resourceAsListOfString(path.toFile().name).map { it.toCharArray() }
    }

    fun tilt(platform: List<CharArray>): List<CharArray> {
        val nextPlatform = mutableListOf<CharArray>()
        nextPlatform.add(platform[0].copyOf())
        for (lineIndex in 1..platform.lastIndex) {
            val line = platform[lineIndex].copyOf()
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

    fun tiltAll(platform: List<CharArray>): List<CharArray> {
        var backupPlatform = platform.map { it.copyOf() }
        while (true) {
            val nextPlatform = tilt(backupPlatform)
            if (isEqual(backupPlatform, nextPlatform)) {
                return nextPlatform
            }
            backupPlatform = nextPlatform.map { it.copyOf() }
        }
    }

    private fun isEqual(platform1: List<CharArray>, platform2: List<CharArray>): Boolean {
        for (index in platform1.indices) {
            val line1 = platform1[index]
            val line2 = platform2[index]
            if (!(line1 contentEquals line2)) {
                return false
            }
        }
        return true
    }

    fun weight(platform: List<CharArray>): Int {
        var weight = 0
        for (index in platform.lastIndex downTo 0) {
            val line = platform[index]
            val value = platform.size - index
            weight += line.count { it == 'O' } * value
        }
        return weight
    }


}