import java.nio.file.Path

class Day11 {
    fun loadGalaxyImage(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun rowsToExpand(galaxyImage: List<String>): List<Int> {
        val rowsToExpand = mutableListOf<Int>()
        galaxyImage.forEachIndexed { index, line ->
            if (line.all { it == '.' }) rowsToExpand.add(index)
        }
        return rowsToExpand
    }
}