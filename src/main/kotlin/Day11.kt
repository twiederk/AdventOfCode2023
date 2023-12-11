import java.nio.file.Path
import java.nio.file.Paths

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

    fun expandCols(galaxyImage: List<String>, colsToExpand: List<Int>): List<String> {
        val expandedGalaxyImage = mutableListOf<String>()
        for (line in galaxyImage) {
            var newLine = ""
            for (index in galaxyImage[0].indices) {
                if (index in colsToExpand) {
                    newLine += ".."
                } else {
                    newLine += line[index]
                }
            }
            expandedGalaxyImage.add(newLine)
        }
        return expandedGalaxyImage
    }

    fun expandRows(galaxyImage: List<String>, rowsToExpand: List<Int>): List<String> {
        val expandedGalaxyImage = mutableListOf<String>()

        for ((index, line) in galaxyImage.withIndex()) {
            expandedGalaxyImage.add(line)
            if (index in rowsToExpand) {
                expandedGalaxyImage.add(line)
            }
        }
        return expandedGalaxyImage
    }

    fun expandGalaxyImage(galaxyImage: List<String>): List<String> {
        val colsToExpand = colsToExpand(galaxyImage)
        val rowsToExpand = rowsToExpand(galaxyImage)
        val expandedCols = expandCols(galaxyImage, colsToExpand)
        return expandRows(expandedCols, rowsToExpand)
    }

    fun galaxies(galaxyImage: List<String>): List<Point2D> {
        val galaxies = mutableListOf<Point2D>()
        galaxyImage
            .forEachIndexed { y, line ->
                line.forEachIndexed { x, char ->
                    if (char == '#') galaxies.add(Point2D(x, y))
                }
            }
        return galaxies
    }

    fun galaxyPairs(galaxies: List<Point2D>): List<Pair<Point2D, Point2D>> {
        val galaxyPairs = mutableListOf<Pair<Point2D, Point2D>>()
        for (src in galaxies.indices) {
            for (dest in (src + 1)..<galaxies.size) {
                galaxyPairs.add(Pair(galaxies[src], galaxies[dest]))
            }
        }
        return galaxyPairs
    }

    fun sumOfDistances(galaxyPairs: List<Pair<Point2D, Point2D>>): Int {
        return galaxyPairs.sumOf { it.first.manhattenDistance(it.second) }
    }

    fun calcDistanceWithExpand(
        galaxy1: Point2D,
        galaxy2: Point2D,
        rowsToExpand: List<Int>,
        colsToExpand: List<Int>,
        expand: Int
    ): Long {
        val distance = galaxy1.manhattenDistance(galaxy2)
        val colExpand = expand(galaxy1.x, galaxy2.x, colsToExpand, expand)
        val rowExpand = expand(galaxy1.y, galaxy2.y, rowsToExpand, expand)
        return distance + colExpand + rowExpand
    }

    private fun expand(start: Int, end: Int, indices: List<Int>, expand: Int): Long {
        var totalExpand = 0L
        var range = start..end
        if (end < start) {
            range = end..start
        }
        for (index in range) {
            if (index in indices) {
                totalExpand += expand
            }
        }
        return totalExpand
    }

    fun sumOfDistancesWithExpand(
        galaxyPairs: List<Pair<Point2D, Point2D>>,
        rowsToExpand: List<Int>,
        colsToExpand: List<Int>,
        expand: Int
    ): Long {
        return galaxyPairs.sumOf { calcDistanceWithExpand(it.first, it.second, rowsToExpand, colsToExpand, expand) }
    }

}

fun main() {
    val day11 = Day11()
    val galaxyImage = day11.loadGalaxyImage(Paths.get("src", "main", "resources", "Day11_InputData.txt"))
    val expandedGalaxyImage = day11.expandGalaxyImage(galaxyImage)
    val expandedGalaxies = day11.galaxies(expandedGalaxyImage)
    val expandedGalaxyPairs = day11.galaxyPairs(expandedGalaxies)
    val part1 = day11.sumOfDistances(expandedGalaxyPairs)
    println("part1 = $part1")

    val galaxies = day11.galaxies(galaxyImage)
    val galaxyPairs = day11.galaxyPairs(galaxies)
    val rowsToExpand = day11.rowsToExpand(galaxyImage)
    val colsToExpand = day11.colsToExpand(galaxyImage)
    val expand = 1_000_000 - 1
    val part2 = day11.sumOfDistancesWithExpand(galaxyPairs, rowsToExpand, colsToExpand, expand)
    println("part2 = $part2")

}