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

    fun colsToExpand(galaxyImage: List<String>): List<Int> {
        val colsToExpand = mutableListOf<Int>()
        for (index in galaxyImage[0].indices) {
            var column = ""
            for (line in galaxyImage) {
                column += line[index]
            }
            if (column.all { it == '.' }) colsToExpand.add(index)
        }
        return colsToExpand
    }


}