import java.nio.file.Path
import java.nio.file.Paths

class Day18 {

    fun loadDigPlan(path: Path): List<DigInstruction> {
        val lines = Resources.resourceAsListOfString(path.toFile().name)
        return lines.map {
            DigInstruction(
                direction = it[0],
                meters = it.substringAfter(" ").substringBefore(" ").toInt(),
                colorCode = it.substringAfterLast(" ")
            )
        }
    }

    fun part1(digInstructions: List<DigInstruction>): Int {
        val lagoon = Lagoon()
        lagoon.digTrench(digInstructions)
        lagoon.digOut()
        return lagoon.pool.size
    }

    fun decodeColorCodes(digInstructions: List<DigInstruction>): List<DigInstruction> {
        return digInstructions.map { it.decodeColorCode() }
    }
}

data class DigInstruction(
    val direction: Char,
    val meters: Int,
    val colorCode: String
) {
    fun decodeColorCode(): DigInstruction {
        val direction = when (colorCode[7]) {
            '0' -> 'R'
            '1' -> 'D'
            '2' -> 'L'
            else -> 'U'
        }
        val meters = Integer.decode("0x${colorCode.substring(2, 7)}")
        return copy(direction = direction, meters = meters)
    }
}

class Lagoon {
    val digger: Digger = Digger()
    val trench = mutableListOf<Point2D>()
    val pool = mutableSetOf<Point2D>()

    fun digTrench(digInstructions: List<DigInstruction>) {
        trench.addAll(digger.digAll(digInstructions))
    }

    fun digOut() {
        val maxX = trench.maxOf { it.x }
        val minX = trench.minOf { it.x }
        val maxY = trench.maxOf { it.y }
        val minY = trench.minOf { it.y }
        trench.forEach { pool.add(it) }
        for (y in (minY + 1)..maxY) {
            var inside = false
            for (x in minX..maxX) {
                if (Point2D(x, y) in trench && Point2D(x, y - 1) in trench) {
                    inside = !inside
                }
                if (inside) pool.add(Point2D(x, y))
            }
        }
        print()
    }

    private fun print() {
        val maxX = trench.maxOf { it.x }
        val minX = trench.minOf { it.x }
        val maxY = trench.maxOf { it.y }
        val minY = trench.minOf { it.y }
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                if (Point2D(x, y) in pool) print("#")
                else print(".")
            }
            println()
        }
    }

}

class Digger(
    var position: Point2D = Point2D(0, 0),
    var facing: Point2D = Point2D.NORTH
) {

    private val movement = mapOf(
        (Point2D.NORTH to 'R') to Point2D.EAST,
        (Point2D.NORTH to 'L') to Point2D.WEST,
        (Point2D.SOUTH to 'R') to Point2D.EAST,
        (Point2D.SOUTH to 'L') to Point2D.WEST,
        (Point2D.EAST to 'U') to Point2D.NORTH,
        (Point2D.EAST to 'D') to Point2D.SOUTH,
        (Point2D.WEST to 'U') to Point2D.NORTH,
        (Point2D.WEST to 'D') to Point2D.SOUTH,
    )

    fun dig(digInstruction: DigInstruction): List<Point2D> {
        val trench = mutableListOf<Point2D>()
        facing = movement[(facing to digInstruction.direction)]
            ?: throw IllegalArgumentException("Can't turn [facing=$facing, direction=${digInstruction.direction}] $digInstruction")
        for (index in 1..digInstruction.meters) {
            position += facing
            trench.add(position)
        }
        return trench
    }

    fun digAll(digInstructions: List<DigInstruction>): List<Point2D> {
        val trench = mutableListOf<Point2D>()
        digInstructions.forEach { trench.addAll(dig(it)) }
        return trench
    }

}

fun main() {
    val day18 = Day18()
    val digInstructions = day18.loadDigPlan(Paths.get("src", "main", "resources", "Day18_InputData.txt"))
    val part1 = day18.part1(digInstructions)
    println("part1 = $part1")
}