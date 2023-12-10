import java.nio.file.Path

typealias Maze = List<String>

class Day10 {

    fun loadMaze(path: Path): Maze {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun startingPosition(maze: Maze): Point {
        for ((row, line) in maze.withIndex()) {
            for ((column, pipe) in line.withIndex()) {
                if (pipe == 'S') {
                    return Point(column, row)
                }
            }
        }
        throw IllegalStateException("Can't find starting point")
    }
}