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

    fun aStar(heatMap: List<String>): List<HeatNode> {
        val openList = PriorityQueue<HeatNode>()
        val closedList = mutableSetOf<HeatNode>()

        val start = HeatNode(Point2D(0, 0), EAST, 0)
        val end = HeatNode(Point2D(heatMap[0].length - 1, heatMap.size - 1))

        // Initialisierung der Open List, die Closed List ist noch leer
        // (die Priorität bzw. der f-Wert des Startknotens ist unerheblich)
        openList.addAll(start.neighbors(heatMap))
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
            val expandedNotes = expandNode(heatMap, currentHeatNode, closedList, openList.toList())
            expandedNotes.filter { !openList.contains(it) }.forEach { openList.add(it) }

            round++

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
        openList: List<HeatNode>
    ): List<HeatNode> {
        val expandedNodes = mutableListOf<HeatNode>()
        for (successor in currentHeatNode.neighbors(heatMap)) {
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
        val path = aStar(heatMap)
        return sumHeatLoss(heatMap, path)
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

    fun neighbors(heatMap: List<String>): List<HeatNode> {
        return directions.getValue(direction)
            .filter { isOnHeatMap(heatMap, coords + it) }
            .filter { isValidMove(it) }
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


    private fun isValidMove(nextDirection: Point2D): Boolean {
        val steps = if (direction == nextDirection) steps + 1 else 1
        return steps <= 3
    }

    fun getHeatLoss(heatMap: List<String>): Int {
        return heatMap[coords.y][coords.x].digitToInt()
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
    val heatLoss = day17.part1(heatMap)
    println("part1: $heatLoss")
}