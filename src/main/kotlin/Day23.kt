import java.nio.file.Path
import java.util.*

class Day23 {

    private val neighbors = mapOf(
        '.' to listOf(Point2D.EAST, Point2D.SOUTH, Point2D.WEST, Point2D.NORTH),
        '>' to listOf(Point2D.EAST),
        '<' to listOf(Point2D.WEST),
        '^' to listOf(Point2D.NORTH),
        'v' to listOf(Point2D.SOUTH)
    )

    fun loadMapOfHikingTrails(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun findAllPaths(mapOfHikingTrails: List<String>): List<HikingPath> {
        val paths = mutableListOf<HikingPath>()
        val work: Queue<HikingPath> = LinkedList()
        work.add(HikingPath(Point2D(1, 0), emptyList()))

        val goal = Point2D(mapOfHikingTrails[0].length - 2, mapOfHikingTrails.size - 1)

        while (work.isNotEmpty()) {
            val current = work.remove()
            if (current.position == goal) {
                paths.add(current.copy(path = current.path + current))
                continue
            }
            val sign = mapOfHikingTrails[current.position.y][current.position.x]
            neighbors[sign]!!
                .filter { isSafe(mapOfHikingTrails, current, it) }
                .filter { !isForest(mapOfHikingTrails, current, it) }
                .filter { !isVisited(current, it) }
                .forEach {
                    work.add(HikingPath(current.position + it, current.path + current))
                }
        }
        return paths
    }

    private fun isSafe(mapOfHikingTrails: List<String>, current: HikingPath, direction: Point2D): Boolean {
        val next = current.position + direction
        return next.x >= 0 && next.x < mapOfHikingTrails[0].length && next.y >= 0 && next.y < mapOfHikingTrails.size
    }

    private fun isVisited(current: HikingPath, it: Point2D) =
        (current.path.map { it.position }.contains(current.position + it))

    private fun isForest(
        mapOfHikingTrails: List<String>,
        current: HikingPath,
        it: Point2D
    ): Boolean {
        val next = current.position + it
        return mapOfHikingTrails[next.y][next.x] == '#'
    }

    fun renderMapWithPath(mapOfHikingTrails: List<String>, path: List<Point2D>): String {
        val map = mapOfHikingTrails.toMutableList()
        path.forEach {
            map[it.y] = map[it.y].replaceRange(it.x, it.x + 1, "O")
        }
        return map.joinToString("\n")
    }

    fun part1(mapOfHikingTrails: List<String>): Int {
        val paths = findAllPaths(mapOfHikingTrails)
        return paths.maxOf { it.path.size } - 1
    }
}


data class HikingPath(
    val position: Point2D,
    val path: List<HikingPath>
)


fun main() {
    val day23 = Day23()
    val mapOfHikingTrails = day23.loadMapOfHikingTrails(Path.of("src/main/resources/Day23_InputData.txt"))
    val part1 = day23.part1(mapOfHikingTrails)
    println("Part 1: $part1")
}