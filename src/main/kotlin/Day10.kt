import java.nio.file.Path

typealias Maze = List<String>

class Day10 {

    fun loadMaze(path: Path): Maze {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun startingPosition(maze: Maze): MazePoint {
        for ((row, line) in maze.withIndex()) {
            for ((column, pipe) in line.withIndex()) {
                if (pipe == 'S') {
                    return MazePoint(column, row, 'S')
                }
            }
        }
        throw IllegalStateException("Can't find starting point")
    }

    //    "-L|F7",
//    "7S-7|",
//    "L|7||",
//    "-L-J|",
//    "L|-JF",
    fun mainPipe(maze: List<String>, start: MazePoint, direction: MazePoint): Int {
        var current = start
        var length = 0
        var nextDirection = direction
        while (current.pipe != 'S') {
            length++
            nextDirection = current.next(nextDirection)
            current += nextDirection
            current = current.copy(pipe = maze[current.y][current.x])
        }
        return length / 2
    }

}

data class MazePoint(
    val x: Int,
    val y: Int,
    val pipe: Char = '.'
) {

    companion object {
        val NORTH = MazePoint(0, -1)
        val EAST = MazePoint(1, 0)
        val SOUTH = MazePoint(0, 1)
        val WEST = MazePoint(-1, 0)

        val movements: Map<Pair<Char, MazePoint>, MazePoint> =
            mapOf(
                ('|' to SOUTH) to SOUTH,
                ('|' to NORTH) to NORTH,
                ('-' to EAST) to EAST,
                ('-' to WEST) to WEST,
                ('L' to WEST) to NORTH,
                ('L' to SOUTH) to EAST,
                ('J' to SOUTH) to WEST,
                ('J' to EAST) to NORTH,
                ('7' to EAST) to SOUTH,
                ('7' to NORTH) to WEST,
                ('F' to WEST) to SOUTH,
                ('F' to NORTH) to EAST
            )
    }

    fun cardinalNeighbors(maze: List<String>): List<MazePoint> {
        val neighbors = mutableListOf<MazePoint>()
        // north
        if (y - 1 >= 0) neighbors.add(this + NORTH)
        // south
        if (y + 1 < maze.size) neighbors.add(this + SOUTH)
        // west
        if (x - 1 >= 0) neighbors.add(this + WEST)
        // east
        if (x + 1 < maze[0].length) neighbors.add(this + EAST)
        return neighbors
    }

    operator fun minus(other: MazePoint): MazePoint =
        MazePoint(x - other.x, y - other.y, other.pipe)

    operator fun plus(other: MazePoint): MazePoint =
        MazePoint(x + other.x, y + other.y, other.pipe)

    fun next(fromDirection: MazePoint): MazePoint {
        return movements[(pipe to fromDirection)]
            ?: throw IllegalArgumentException("Can't move from $this coming from $fromDirection")
    }

}

