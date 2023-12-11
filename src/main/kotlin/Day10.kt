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

    //    | is a vertical pipe connecting north and south.
//    - is a horizontal pipe connecting east and west.
//    L is a 90-degree bend connecting north and east.
//    J is a 90-degree bend connecting north and west.
//    7 is a 90-degree bend connecting south and west.
//    F is a 90-degree bend connecting south and east.
//    . is ground; there is no pipe in this tile.
//  OK  S is the starting position of the animal; there is a pipe on this
    fun isConnected(source: Char, pipe: Char): Boolean {
        if (pipe == 'S') return true
        if (pipe == '|') return true
        return false
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
        MazePoint(x - other.x, y - other.y, pipe)

    operator fun plus(other: MazePoint): MazePoint =
        MazePoint(x + other.x, y + other.y, pipe)

}

