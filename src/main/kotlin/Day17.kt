import java.nio.file.Path

class Day17 {

    fun loadHeatMap(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun aStar(heatMap: List<String>): Int {
        return 0
    }

    // überprüft alle Nachfolgeknoten und fügt sie der Open List hinzu, wenn entweder
    // - der Nachfolgeknoten zum ersten Mal gefunden wird, oder
    // - ein besserer Weg zu diesem Knoten gefunden wird
    fun expandNode(
        heatMap: List<String>,
        currentHeatNode: HeatNode,
        closedList: List<HeatNode>,
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
            if (openList.contains(successor)) {
                println(tentativeG >= openList[openList.indexOf(successor)].g)
            }
            if (openList.contains(successor) && tentativeG >= openList[openList.indexOf(successor)].g) {
                continue
            }

            // Vorgängerzeiger setzen und g Wert merken oder anpassen
            successor.parent = currentHeatNode
            successor.g = tentativeG

//            // f-Wert des Knotens in der Open List aktualisieren
//            // bzw. Knoten mit f-Wert in die Open List einfügen
//            val f = tentativeG + distance(successor, end)
//
//            successor.f = f
//            if (!openList.contains(successor)) {
//                openList.add(successor)
//            }
            expandedNodes.add(successor)
        }
        return expandedNodes
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