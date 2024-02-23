import java.nio.file.Path

class Day17 {

    fun loadHeatMap(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun aStar(heatMap: List<String>): Int {
        return 0
    }

    fun expandNode(heatMap: List<String>, currentAStarNode: AStarNode, closedList: List<AStarNode>): List<AStarNode> {
        return currentAStarNode.neighbors(heatMap).filter { !closedList.contains(it) }
    }

}

data class AStarNode(
    val coords: Point2D
) {
    fun neighbors(heatMap: List<String>): List<AStarNode> {
        return coords.cardinalNeighbors(heatMap).map { AStarNode(it) }
    }
}