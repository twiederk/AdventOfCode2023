import java.nio.file.Path

class Day17 {

    fun loadHeatMap(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun aStar(heatMap: List<String>): Int {
        return 0
    }

    fun expandNode(heatMap: List<String>, currentHeatNode: HeatNode): List<HeatNode> {
        return currentHeatNode.neighbors(heatMap)
    }

}

data class HeatNode(
    val coords: Point2D
) {
    fun neighbors(heatMap: List<String>): List<HeatNode> {
        return coords.cardinalNeighbors(heatMap).map { HeatNode(it) }
    }
}