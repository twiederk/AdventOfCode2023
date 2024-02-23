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
    fun expandNode(heatMap: List<String>, currentAStarNode: AStarNode, closedList: List<AStarNode>): List<AStarNode> {
        for (successor in currentAStarNode.neighbors(heatMap)) {
            if (closedList.contains(successor)) {
                continue
            }

            // g-Wert für den neuen Weg berechnen: g-Wert des Vorgängers plus
            // die Kosten der gerade benutzten Kante
            val tentativeG = currentAStarNode.g + currentAStarNode.getHeatLoss(heatMap)

//            // wenn der Nachfolgeknoten bereits auf der Open List ist,
//            // aber der neue Weg nicht besser ist als der alte – tue nichts
//            if (openList.contains(successor) && tentativeG >= successor.g) {
//                continue
//            }
//
//            // Vorgängerzeiger setzen und g Wert merken oder anpassen
//            successor.parent = currentNode
//            successor.g = tentativeG
//
//            // f-Wert des Knotens in der Open List aktualisieren
//            // bzw. Knoten mit f-Wert in die Open List einfügen
//            val f = tentativeG + distance(successor, end)
//
//            successor.f = f
//            if (!openList.contains(successor)) {
//                openList.add(successor)
//            }
        }
        return currentAStarNode.neighbors(heatMap).filter { !closedList.contains(it) }
    }

}

data class AStarNode(
    val coords: Point2D
) : Comparable<AStarNode> {
    var f: Int = 0
    var g: Int = 0
    var parent: AStarNode? = null

    override fun compareTo(other: AStarNode): Int = f.compareTo(other.f)

    fun neighbors(heatMap: List<String>): List<AStarNode> {
        return coords.cardinalNeighbors(heatMap).map { AStarNode(it) }
    }

    fun getHeatLoss(heatMap: List<String>): Int {
        return heatMap[coords.y][coords.x].digitToInt()
    }
}