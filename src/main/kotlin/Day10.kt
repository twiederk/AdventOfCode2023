import java.nio.file.Path
import java.nio.file.Paths

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
    fun mainPipe(maze: List<String>, start: MazePoint, direction: MazePoint): List<MazePoint> {
        var current = start
        var length = 0
        var nextDirection = direction
        val mainPipe = mutableListOf<MazePoint>()
        while (current.pipe != 'S') {
            length++
            mainPipe.add(current)
            nextDirection = current.next(nextDirection)
            current += nextDirection
            current = current.copy(pipe = maze[current.y][current.x])
        }
        return mainPipe
    }

    fun part1(maze: Maze, start: MazePoint, direction: MazePoint): Int {
        return mainPipe(maze, start, direction).size / 2
    }

    fun cleanMaze(maze: Maze, mainPipe: List<MazePoint>): List<String> {
        val cleanMaze = mutableListOf<String>()
        val mainPipePoint2D = mainPipe.map { Point2D(it.x, it.y) }
        for (row in maze.indices) {
            val newLine = maze[row].toCharArray()
            for (col in maze[0].indices) {
                val currPoint = Point2D(col, row)
                if (currPoint in mainPipePoint2D) {
                    newLine[col] = maze[row][col]
                } else {
                    newLine[col] = '.'
                }
            }
            cleanMaze.add(String(newLine))
        }
        return cleanMaze
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


fun main() {
    val day10 = Day10()
    val maze = day10.loadMaze(Paths.get("src", "main", "resources", "Day10_InputData.txt"))
    val start = day10.startingPosition(maze).copy(pipe = '|')
    val part1 = day10.part1(maze, start, MazePoint.SOUTH)
    println("part1 = $part1")
}