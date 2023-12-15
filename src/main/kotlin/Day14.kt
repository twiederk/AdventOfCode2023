import java.nio.file.Path
import java.nio.file.Paths

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

    fun part1(platform: List<CharArray>): Int {
        val tiltedPlatform = tiltAll(platform)
        return weight(tiltedPlatform)
    }

    fun rotate(platform: List<CharArray>): List<CharArray> {
        val rotatedPlatform = mutableListOf<CharArray>()
        for (col in platform[0].indices) {
            var line = ""
            for (row in platform.lastIndex downTo 0) {
                line += "${platform[row][col]}"
            }
            rotatedPlatform.add(line.toCharArray())
        }
        return rotatedPlatform
    }

    fun cycle(platform: List<CharArray>, numberOfCycles: Int): List<CharArray> {
        var rotatedPlatform = platform
        for (loop in 1..numberOfCycles) {
            // NORTH
            var tiltedPlatform = tiltAll(rotatedPlatform)
            // WEST
            rotatedPlatform = rotate(tiltedPlatform)
            tiltedPlatform = tiltAll(rotatedPlatform)
            // SOUTH
            rotatedPlatform = rotate(tiltedPlatform)
            tiltedPlatform = tiltAll(rotatedPlatform)
            // EAST
            rotatedPlatform = rotate(tiltedPlatform)
            tiltedPlatform = tiltAll(rotatedPlatform)

            rotatedPlatform = rotate(tiltedPlatform)
        }
        return rotatedPlatform
    }


}

fun main() {
    val day14 = Day14()
    val platform = day14.loadPlatform(Paths.get("src", "main", "resources", "Day14_InputData.txt"))
    val part1 = day14.part1(platform)
    println("part1 = $part1")
}