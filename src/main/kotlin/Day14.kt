import java.nio.file.Path
import java.nio.file.Paths

class Day14 {

    fun loadParabolicReflector(path: Path): Platform {
        return Platform(Resources.resourceAsListOfString(path.toFile().name).map { it.toCharArray() })
    }

    fun tilt(platform: Platform): Platform {
        val nextPlatform = mutableListOf<CharArray>()
        nextPlatform.add(platform.data[0].copyOf())
        for (lineIndex in 1..platform.data.lastIndex) {
            val line = platform.data[lineIndex].copyOf()
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
        return Platform(nextPlatform)
    }

    fun tiltAll(platform: Platform): Platform {
        var backupPlatform = platform.copy()
        while (true) {
            val nextPlatform = tilt(backupPlatform)
            if (isEqual(backupPlatform, nextPlatform)) {
                return nextPlatform
            }
            backupPlatform = nextPlatform.copy()
        }
    }

    private fun isEqual(platform1: Platform, platform2: Platform): Boolean {
        for (index in platform1.data.indices) {
            val line1 = platform1.data[index]
            val line2 = platform2.data[index]
            if (!(line1 contentEquals line2)) {
                return false
            }
        }
        return true
    }

    fun weight(platform: Platform): Int {
        var weight = 0
        for (index in platform.data.lastIndex downTo 0) {
            val line = platform.data[index]
            val value = platform.data.size - index
            weight += line.count { it == 'O' } * value
        }
        return weight
    }

    fun part1(platform: Platform): Int {
        val tiltedPlatform = tiltAll(platform)
        return weight(tiltedPlatform)
    }

    fun rotate(platform: Platform): Platform {
        val rotatedPlatform = mutableListOf<CharArray>()
        for (col in platform.data[0].indices) {
            var line = ""
            for (row in platform.data.lastIndex downTo 0) {
                line += "${platform.data[row][col]}"
            }
            rotatedPlatform.add(line.toCharArray())
        }
        return Platform(rotatedPlatform)
    }

    fun cycle(platform: Platform): Platform {
        // NORTH
        var tiltedPlatform = tiltAll(platform)
        // WEST
        var rotatedPlatform = rotate(tiltedPlatform)
        tiltedPlatform = tiltAll(rotatedPlatform)
        // SOUTH
        rotatedPlatform = rotate(tiltedPlatform)
        tiltedPlatform = tiltAll(rotatedPlatform)
        // EAST
        rotatedPlatform = rotate(tiltedPlatform)
        tiltedPlatform = tiltAll(rotatedPlatform)

        return rotate(tiltedPlatform)
    }

    fun part2(platform: Platform, goal: Int): Int {
        val seen = mutableMapOf<Int, Int>()
        var cycleNumber = 1
        var nextPlatform = platform
        while (cycleNumber <= goal) {
            nextPlatform = cycle(nextPlatform)
            val key = nextPlatform.data.sumOf { it.joinToString("").hashCode() }
            if (!seen.contains(key)) {
                seen[key] = cycleNumber++
            } else {
                val cycleLength = cycleNumber - seen.getValue(key)
                val cyclesRemaining = (goal - cycleNumber) % cycleLength
                repeat(cyclesRemaining) {
                    nextPlatform = cycle(nextPlatform)
                }
                return weight(nextPlatform)
            }
        }
        return weight(nextPlatform)
    }

}

data class Platform(
    val data: List<CharArray>
)

fun main() {
    val day14 = Day14()
    val parabolicReflector = day14.loadParabolicReflector(Paths.get("src", "main", "resources", "Day14_InputData.txt"))
    val part1 = day14.part1(parabolicReflector)
    println("part1 = $part1")

    val part2 = day14.part2(parabolicReflector, 1_000_000_000)
    println("part2 = $part2")
}