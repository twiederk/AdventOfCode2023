import java.nio.file.Path

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

    val visitedTiles = mutableSetOf(Beam(Point2D(0, 0), Point2D.EAST))

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

    fun energize(): Int {
        var beams = mutableListOf(visitedTiles.first())
        var backupVisitedTileSize = visitedTiles.size
        while (beams.isNotEmpty()) {
            beams = nextMultiple(beams).toMutableList()
            if (backupVisitedTileSize == visitedTiles.size) {
                break
            }
            backupVisitedTileSize = visitedTiles.size

        }
        return visitedTiles.size
    }
}