import java.nio.file.Path
import java.util.*

class Day17 {

    fun loadHeatMap(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun aStar(
        heatMap: List<String>,
        maxRounds: Int = Integer.MAX_VALUE,
        debug: Boolean = false
    ): List<HeatNode> {
        val openList = PriorityQueue<HeatNode>()
        val closedList = mutableSetOf<HeatNode>()

        val start = HeatNode(Point2D(0, 0))
        val end = HeatNode(Point2D(heatMap[0].length - 1, heatMap.size - 1))

        // Initialisierung der Open List, die Closed List ist noch leer
        // (die Priorität bzw. der f-Wert des Startknotens ist unerheblich)
        openList.add(start)
        // diese Schleife wird durchlaufen bis entweder
        // - die optimale Lösung gefunden wurde oder
        // - feststeht, dass keine Lösung existiert
        var round = 0
        do {
            // Knoten mit dem geringsten f-Wert aus der Open List entfernen
            val currentHeatNode = openList.remove()
            debug("######## round $round #############", debug)
            debug("currentNode = $currentHeatNode", debug)

            // Wurde das Ziel gefunden?
            if (currentHeatNode == end) {
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
//            debug(render(grid), debug)

        } while (openList.isNotEmpty() && round < maxRounds)

        // die Open List ist leer, es existiert kein Pfad zum Ziel
        return emptyList()
    }

    fun getPath(end: HeatNode, closedList: List<HeatNode>): List<HeatNode> {
        var currentNode: HeatNode? = closedList.first { it == end }
        val path = mutableListOf<HeatNode>()
        while (currentNode != null) {
            path.add(currentNode)
            currentNode = currentNode.parent
        }
        return path.reversed()
    }

    private fun debug(message: String, debug: Boolean) {
        if (debug) {
            println(message)
        }
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
        return path.dropLast(1).sumOf { it.getHeatLoss(heatMap) }
    }

    fun part1(heatMap: List<String>): Int {
        val path = aStar(heatMap)
        return sumHeatLoss(heatMap, path)
    }

}

data class HeatNode(
    val coords: Point2D
) : Comparable<HeatNode> {
    var f: Int = 0
    var g: Int = 0
    var parent: HeatNode? = null

    override fun compareTo(other: HeatNode): Int = f.compareTo(other.f)

    fun neighbors(heatMap: List<String>): List<HeatNode> {
        return coords.cardinalNeighbors(heatMap).map { HeatNode(it) }
    }

    fun getHeatLoss(heatMap: List<String>): Int {
        return heatMap[coords.y][coords.x].digitToInt()
    }
}