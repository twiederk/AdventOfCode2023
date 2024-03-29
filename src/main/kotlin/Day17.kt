import Point2D.Companion.EAST
import Point2D.Companion.NORTH
import Point2D.Companion.SOUTH
import Point2D.Companion.WEST
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

class Day17 {

    fun loadHeatMap(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun aStar(
        heatMap: List<String>,
        isValidMove: (Point2D, Point2D, Int) -> Boolean,
        debug: Boolean = false
    ): List<HeatNode> {
        val openList = PriorityQueue<HeatNode>()
        val closedList = mutableSetOf<HeatNode>()

        val start = HeatNode(Point2D(0, 0), EAST, 0)
        val end = HeatNode(Point2D(heatMap[0].length - 1, heatMap.size - 1))

        // Initialisierung der Open List, die Closed List ist noch leer
        // (die Priorität bzw. der f-Wert des Startknotens ist unerheblich)
        openList.addAll(start.neighbors(heatMap, isValidMove))
        // diese Schleife wird durchlaufen bis entweder
        // - die optimale Lösung gefunden wurde oder
        // - feststeht, dass keine Lösung existiert
        var round = 0
        do {
            // Knoten mit dem geringsten f-Wert aus der Open List entfernen
            val currentHeatNode = openList.remove()

            // Wurde das Ziel gefunden?
            if (currentHeatNode.coords == end.coords) {
                closedList.add(currentHeatNode)
                return getPath(end, closedList.toList())
            }

            // Der aktuelle Knoten soll durch nachfolgende Funktionen
            // nicht weiter untersucht werden, damit keine Zyklen entstehen
            closedList.add(currentHeatNode)

            // Wenn das Ziel noch nicht gefunden wurde: Nachfolgeknoten
            // des aktuellen Knotens auf die Open List setzen
            val expandedNotes = expandNode(heatMap, currentHeatNode, closedList, openList.toList(), isValidMove)
            expandedNotes.filter { !openList.contains(it) }.forEach { openList.add(it) }

            round++
            if (debug) {
                renderStep(heatMap, openList, closedList, round)
            }

        } while (openList.isNotEmpty() && round < Integer.MAX_VALUE)

        // die Open List ist leer, es existiert kein Pfad zum Ziel
        return emptyList()
    }

    fun getPath(end: HeatNode, closedList: List<HeatNode>): List<HeatNode> {
        var currentNode: HeatNode? = closedList.first { it.coords == end.coords }
        val path = mutableListOf<HeatNode>()
        while (currentNode != null) {
            path.add(currentNode)
            currentNode = currentNode.parent
        }
        return path.reversed()
    }

    // überprüft alle Nachfolgeknoten und fügt sie der Open List hinzu, wenn entweder
    // - der Nachfolgeknoten zum ersten Mal gefunden wird, oder
    // - ein besserer Weg zu diesem Knoten gefunden wird
    fun expandNode(
        heatMap: List<String>,
        currentHeatNode: HeatNode,
        closedList: Set<HeatNode>,
        openList: List<HeatNode>,
        isValidMove: (Point2D, Point2D, Int) -> Boolean
    ): List<HeatNode> {
        val expandedNodes = mutableListOf<HeatNode>()
        for (successor in currentHeatNode.neighbors(heatMap, isValidMove)) {
            if (closedList.contains(successor)) {
                continue
            }

            // g-Wert für den neuen Weg berechnen: g-Wert des Vorgängers plus
            // die Kosten der gerade benutzten Kante
            val tentativeG = currentHeatNode.g + currentHeatNode.getHeatLoss(heatMap)

            // wenn der Nachfolgeknoten bereits auf der Open List ist,
            // aber der neue Weg nicht besser ist als der alte – tue nichts
            if (openList.contains(successor) && tentativeG >= openList[openList.indexOf(successor)].g) {
                continue
            }

            // Vorgängerzeiger setzen und g Wert merken oder anpassen
            successor.parent = currentHeatNode
            successor.g = tentativeG

            // f-Wert des Knotens in der Open List aktualisieren
            // bzw. Knoten mit f-Wert in die Open List einfügen
            val end = Point2D(heatMap[0].length - 1, heatMap.size - 1)
            val f = tentativeG + successor.coords.manhattenDistance(end)
            successor.f = f

            expandedNodes.add(successor)
        }
        return expandedNodes
    }

    fun sumHeatLoss(heatMap: List<String>, path: List<HeatNode>): Int {
        return path.sumOf { it.getHeatLoss(heatMap) }
    }

    fun part1(heatMap: List<String>): Int {
        val path = aStar(heatMap, HeatNode::isValidMovePart1)
        renderSolution(heatMap, path)
        return sumHeatLoss(heatMap, path)
    }

    fun part2(heatMap: List<String>): Int {
        val path = aStar(heatMap, HeatNode::isValidMovePart2)
        renderSolution(heatMap, path)
        return sumHeatLoss(heatMap, path)
    }

    private fun renderSolution(heatMap: List<String>, path: List<HeatNode>) {
        val pathDirections = path.associate { (it.coords to it.direction) }
        for ((row, line) in heatMap.withIndex()) {
            for ((col, char) in line.withIndex()) {
                val coords = Point2D(col, row)
                if (pathDirections.contains(coords)) {
                    when (pathDirections[coords]) {
                        NORTH -> print('^')
                        SOUTH -> print('v')
                        WEST -> print('<')
                        EAST -> print('>')
                    }
                } else {
                    print(char)
                }
            }
            println()
        }
    }

    private fun renderStep(
        heatMap: List<String>,
        openList: PriorityQueue<HeatNode>,
        closedList: MutableSet<HeatNode>,
        step: Int
    ) {
        println("************* $step *************")
        val openDirections = openList.associate { (it.coords to it.direction) }
        val closedDirections = closedList.associate { (it.coords to it.direction) }
        for ((row, line) in heatMap.withIndex()) {
            for ((col, char) in line.withIndex()) {
                val coords = Point2D(col, row)
                if (openDirections.contains(coords)) {
                    print('O')
                } else if (closedDirections.contains(coords)) {
                    print('C')
                } else {
                    print(char)
                }
            }
            println()
        }
    }

}

data class HeatNode(
    val coords: Point2D,
    val direction: Point2D = EAST,
    val steps: Int = 1
) : Comparable<HeatNode> {
    var f: Int = 0
    var g: Int = 0
    var parent: HeatNode? = null

    override fun compareTo(other: HeatNode): Int = f.compareTo(other.f)

    fun neighbors(heatMap: List<String>, isValidMove: (Point2D, Point2D, Int) -> Boolean): List<HeatNode> {
        return directions.getValue(direction)
            .filter { isOnHeatMap(heatMap, coords + it) }
            .filter { isValidMove(this.direction, it, this.steps) }
            .map { nextDirection ->
                HeatNode(
                    coords = coords + nextDirection,
                    direction = nextDirection,
                    steps = if (direction == nextDirection) steps + 1 else 1
                )
            }
    }

    private fun isOnHeatMap(heatMap: List<String>, location: Point2D): Boolean {
        return location.x >= 0 && location.x < heatMap[0].length
                && location.y >= 0 && location.y < heatMap.size
    }

    fun getHeatLoss(heatMap: List<String>): Int {
        return heatMap[coords.y][coords.x].digitToInt()
    }

    companion object {
        fun isValidMovePart1(direction: Point2D, nextDirection: Point2D, steps: Int): Boolean {
            val nextSteps = if (direction == nextDirection) steps + 1 else 1
            return nextSteps <= 3
        }

        fun isValidMovePart2(direction: Point2D, nextDirection: Point2D, steps: Int): Boolean {
            return when (steps) {
                in 0..3 -> direction == nextDirection
                in 4..9 -> true
                else -> direction != nextDirection
            }
        }

    }
}

private val directions = mapOf(
    NORTH to setOf(NORTH, EAST, WEST),
    WEST to setOf(WEST, NORTH, SOUTH),
    SOUTH to setOf(SOUTH, EAST, WEST),
    EAST to setOf(EAST, NORTH, SOUTH)
)

fun main() {
    val day17 = Day17()
    val heatMap = day17.loadHeatMap(Paths.get("src", "main", "resources", "Day17_InputData.txt"))
    println("part1: ${day17.part1(heatMap)}")
    println("part2: ${day17.part2(heatMap)}")
}

