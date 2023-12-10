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
}

data class MazePoint(
    val x: Int,
    val y: Int,
    val pipe: Char = '.'
) {
    fun neighbors(maze: List<String>): List<MazePoint> {
        val neighbors = mutableListOf<MazePoint>()
        // north
        if (y - 1 >= 0) neighbors.add(MazePoint(x, y - 1, maze[y - 1][x]))
        // south
        if (y + 1 < maze.size) neighbors.add(MazePoint(x, y + 1, maze[y + 1][x]))
        // west
        if (x - 1 >= 0) neighbors.add(MazePoint(x - 1, y, maze[y][x - 1]))
        // east
        if (x + 1 < maze[0].length) neighbors.add(MazePoint(x + 1, y, maze[y][x + 1]))

        return neighbors
    }
}
