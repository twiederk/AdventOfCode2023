import java.nio.file.Path
import java.nio.file.Paths

class Day16 {

    fun loadContraption(path: Path): Contraption {
        return Contraption(Resources.resourceAsListOfString(path.toFile().name))
    }

}

data class Beam(
    val position: Point2D,
    val heading: Point2D
)

data class Contraption(
    val data: List<String>
) {

    var visitedTiles = mutableSetOf(Beam(Point2D(0, 0), Point2D.EAST))

    private val movements: Map<Pair<Char, Point2D>, List<Point2D>> =
        mapOf(
            // NORTH = ^
            // SOUTH = v
            // WEST = <
            // EAST = >
            ('.' to Point2D.NORTH) to listOf(Point2D.NORTH),
            ('.' to Point2D.SOUTH) to listOf(Point2D.SOUTH),
            ('.' to Point2D.EAST) to listOf(Point2D.EAST),
            ('.' to Point2D.WEST) to listOf(Point2D.WEST),

            ('/' to Point2D.NORTH) to listOf(Point2D.EAST),
            ('/' to Point2D.SOUTH) to listOf(Point2D.WEST),
            ('/' to Point2D.EAST) to listOf(Point2D.NORTH),
            ('/' to Point2D.WEST) to listOf(Point2D.SOUTH),

            ('\\' to Point2D.NORTH) to listOf(Point2D.WEST),
            ('\\' to Point2D.SOUTH) to listOf(Point2D.EAST),
            ('\\' to Point2D.EAST) to listOf(Point2D.SOUTH),
            ('\\' to Point2D.WEST) to listOf(Point2D.NORTH),

            ('|' to Point2D.NORTH) to listOf(Point2D.NORTH),
            ('|' to Point2D.SOUTH) to listOf(Point2D.SOUTH),
            ('|' to Point2D.EAST) to listOf(Point2D.NORTH, Point2D.SOUTH),
            ('|' to Point2D.WEST) to listOf(Point2D.NORTH, Point2D.SOUTH),

            ('-' to Point2D.NORTH) to listOf(Point2D.WEST, Point2D.EAST),
            ('-' to Point2D.SOUTH) to listOf(Point2D.WEST, Point2D.EAST),
            ('-' to Point2D.EAST) to listOf(Point2D.EAST),
            ('-' to Point2D.WEST) to listOf(Point2D.WEST),
        )


    fun nextSingle(beam: Beam): List<Beam> {
        val char = data[beam.position.y][beam.position.x]
        val headings = movements[(char to beam.heading)] ?: throw IllegalStateException("Can't find heading")

        val nextBeams = mutableListOf<Beam>()
        headings.forEach { heading -> nextBeams.add(Beam(beam.position + heading, heading)) }
        return nextBeams.filter { it.position.y >= 0 && it.position.x < data[0].length && it.position.y < data.size && it.position.x >= 0 }
    }

    fun nextMultiple(beams: List<Beam>): List<Beam> {
        val nextBeams = mutableListOf<Beam>()
        beams.forEach { beam -> nextBeams.addAll(nextSingle(beam)) }
        visitedTiles.addAll(nextBeams)
        return nextBeams
    }

    fun energize(startingBeam: Beam): Int {
        visitedTiles = mutableSetOf(startingBeam)
        var beams = mutableListOf(startingBeam)
        var backupVisitedTileSize = visitedTiles.size
        while (beams.isNotEmpty()) {
            beams = nextMultiple(beams).toMutableList()
            if (backupVisitedTileSize == visitedTiles.size) {
                break
            }
            backupVisitedTileSize = visitedTiles.size

        }
        return visitedTiles.map { it.position }.toSet().size
    }

    fun energizeTop(): Int {
        var maxEnergizedTiles = 0
        for (x in data[0].indices) {
            val energizedTiles = energize(Beam(Point2D(x, 0), Point2D.SOUTH))
            if (energizedTiles > maxEnergizedTiles) {
                maxEnergizedTiles = energizedTiles
            }
        }
        return maxEnergizedTiles
    }
}

fun main() {
    val day16 = Day16()
    val contraption = day16.loadContraption(Paths.get("src", "main", "resources", "Day16_InputData.txt"))
    val part1 = contraption.energize(Beam(Point2D(0, 0), Point2D.EAST))
    println("part1 = $part1")
}