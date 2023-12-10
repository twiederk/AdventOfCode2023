import java.nio.file.Path

typealias Maze = List<String>

class Day10 {

    fun loadMaze(path: Path): Maze {
        return Resources.resourceAsListOfString(path.toFile().name)
    }
}