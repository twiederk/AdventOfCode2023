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
        val x = if (coords.x >= 0) {
            coords.x % mapOfGarden[0].length
        } else {
            mapOfGarden[0].length + (coords.x % mapOfGarden[0].length)
        }
        val y = if (coords.y >= 0) {
            coords.y % mapOfGarden.size
        } else {
            mapOfGarden.size + (coords.y % mapOfGarden.size)
        }
        return mapOfGarden[y][x] == '#'
    }

    fun render(mapOfGard: List<String>, finalSteps: List<Point2D>): String {
        val map = mapOfGard.toMutableList()
        finalSteps.forEach { map[it.y] = map[it.y].replaceRange(it.x, it.x + 1, "O") }
        return map.joinToString("\n")
    }

    fun bfs(mapOfGarden: List<String>, startingPosition: Point2D, maxSteps: Int): List<Point2D> {

        val queue = LinkedList<Work>()
        queue.add(Work(startingPosition, 0))
        val finalSteps = mutableListOf<Work>()

        while (queue.isNotEmpty()) {
            val current = queue.remove()
            if (current.steps == maxSteps) {
                finalSteps.add(current)
                continue
            }
            current.position.cardinalNeighbors(mapOfGarden)
                .filter { !isRock(mapOfGarden, it) }
                .filter { it !in queue.map { queueElement -> queueElement.position } }
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

    fun bfsInfinite(mapOfGarden: List<String>, startingPosition: Point2D, maxSteps: Int): List<Point2D> {
        val queue = LinkedList<Work>()
        queue.add(Work(startingPosition, 0))
        val finalSteps = mutableListOf<Work>()

        while (queue.isNotEmpty()) {
            val current = queue.remove()
            if (current.steps == maxSteps) {
                finalSteps.add(current)
                continue
            }
            current.position.cardinalNeighbors()
                .filter { !isRock(mapOfGarden, it) }
                .filter { it !in queue.map { queueElement -> queueElement.position } }
                .forEach {
                    queue.add(Work(it, current.steps + 1))
                }
        }
        return finalSteps.map { it.position }
    }


}

data class Work(
    val position: Point2D,
    val steps: Int
)


fun main() {
    val day21 = Day21()
    val mapOfGarden = day21.loadMapOfGarden(Path.of("src/main/resources/Day21_InputData.txt"))
    val part1 = day21.part1(mapOfGarden, 64)
    println("part1: $part1")
}