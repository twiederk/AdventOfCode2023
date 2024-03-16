import java.nio.file.Path
import java.util.*

class Day21 {

    fun loadMapOfGarden(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun startingPosition(mapOfGarden: List<String>): Point2D {
        val y = mapOfGarden.indexOfFirst { it.contains("S") }
        val x = mapOfGarden[y].indexOfFirst { it == 'S' }
        return Point2D(x, y)
    }

    fun isRock(mapOfGarden: List<String>, coords: Point2D): Boolean {
        return mapOfGarden[coords.y][coords.x] == '#'
    }

    fun render(mapOfGard: List<String>, finalSteps: List<Point2D>): String {
        val map = mapOfGard.toMutableList()
        finalSteps.forEach { map[it.y] = map[it.y].replaceRange(it.x, it.x + 1, "O") }
        return map.joinToString("\n")
    }

    fun bfs(smallestMap: List<String>, startingPosition: Point2D, maxSteps: Int): List<Point2D> {

        val queue = LinkedList<Work>()
        queue.add(Work(startingPosition, 0))
        val finalSteps = mutableListOf<Work>()

        while (queue.isNotEmpty()) {
            val current = queue.remove()
            if (current.steps == maxSteps) {
                finalSteps.add(current)
                continue
            }
            current.position.cardinalNeighbors(smallestMap)
                .filter { !isRock(smallestMap, it) }
                .forEach {
                    queue.add(Work(it, current.steps + 1))
                }
        }
        return finalSteps.map { it.position }
    }

    fun part1(mapOfGarden: List<String>, maxSteps: Int): Int {
        val startingPosition = startingPosition(mapOfGarden)
        val finalSteps = bfs(mapOfGarden, startingPosition, maxSteps)
        return finalSteps.count()
    }


}

data class Work(
    val position: Point2D,
    val steps: Int
)
