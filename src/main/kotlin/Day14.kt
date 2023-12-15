import java.nio.file.Path
import java.nio.file.Paths

class Day14 {

    fun loadPlatform(path: Path): Platform {
        return Platform(Resources.resourceAsListOfString(path.toFile().name).map { it.toCharArray() })
    }


    fun part1(platform: Platform): Int {
        val tiltedPlatform = platform.tilt()
        return tiltedPlatform.weight()
    }

    fun part2(platform: Platform, goal: Int): Int {
        val seen = mutableMapOf<Int, Int>()
        var cycleNumber = 1
        var nextPlatform = platform
        while (cycleNumber <= goal) {
            nextPlatform = nextPlatform.cycle()
            val key = nextPlatform.data.sumOf { it.joinToString("").hashCode() }
            if (!seen.contains(key)) {
                seen[key] = cycleNumber++
            } else {
                val cycleLength = cycleNumber - seen.getValue(key)
                val cyclesRemaining = (goal - cycleNumber) % cycleLength
                repeat(cyclesRemaining) {
                    nextPlatform = nextPlatform.cycle()
                }
                return nextPlatform.weight()
            }
        }
        return nextPlatform.weight()
    }

}

data class Platform(
    val data: List<CharArray>
) {
    fun weight(): Int {
        var weight = 0
        for (index in data.lastIndex downTo 0) {
            val line = data[index]
            val value = data.size - index
            weight += line.count { it == 'O' } * value
        }
        return weight
    }

    fun tiltSingleStep(): Platform {
        val nextPlatform = mutableListOf<CharArray>()
        nextPlatform.add(data[0].copyOf())
        for (lineIndex in 1..data.lastIndex) {
            val line = data[lineIndex].copyOf()
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

    fun tilt(): Platform {
        var backupPlatform = copy()
        while (true) {
            val nextPlatform = backupPlatform.tiltSingleStep()
            if (backupPlatform == nextPlatform) {
                return nextPlatform
            }
            backupPlatform = nextPlatform.copy()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Platform) return false
        for (index in data.indices) {
            val line1 = data[index]
            val line2 = other.data[index]
            if (!(line1 contentEquals line2)) {
                return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    fun rotate(): Platform {
        val rotatedPlatform = mutableListOf<CharArray>()
        for (col in data[0].indices) {
            var line = ""
            for (row in data.lastIndex downTo 0) {
                line += "${data[row][col]}"
            }
            rotatedPlatform.add(line.toCharArray())
        }
        return Platform(rotatedPlatform)
    }

    fun cycle(): Platform {
        // NORTH
        var tiltedPlatform = tilt()
        // WEST
        var rotatedPlatform = tiltedPlatform.rotate()
        tiltedPlatform = rotatedPlatform.tilt()
        // SOUTH
        rotatedPlatform = tiltedPlatform.rotate()
        tiltedPlatform = rotatedPlatform.tilt()
        // EAST
        rotatedPlatform = tiltedPlatform.rotate()
        tiltedPlatform = rotatedPlatform.tilt()

        return tiltedPlatform.rotate()
    }


}

fun main() {
    val day14 = Day14()
    val parabolicReflector = day14.loadPlatform(Paths.get("src", "main", "resources", "Day14_InputData.txt"))
    val part1 = day14.part1(parabolicReflector)
    println("part1 = $part1")

    val part2 = day14.part2(parabolicReflector, 1_000_000_000)
    println("part2 = $part2")
}