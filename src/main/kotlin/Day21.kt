import java.nio.file.Path

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


}