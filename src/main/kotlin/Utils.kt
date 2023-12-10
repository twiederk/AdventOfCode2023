import java.io.File
import java.net.URI


/**
 * Check if IntRanges overlap
 */
infix fun IntRange.overlaps(other: IntRange): Boolean = first <= other.last && other.first <= last

/**
 * Check if InRanges overlap fully
 */
infix fun IntRange.fullyOverlaps(other: IntRange): Boolean = first <= other.first && last >= other.last

/**
 * Check if IntRanges overlap
 */
infix fun LongRange.overlaps(other: LongRange): Boolean = first <= other.last && other.first <= last

/**
 * Check if InRanges overlap fully
 */
infix fun LongRange.fullyOverlaps(other: LongRange): Boolean = first <= other.first && last >= other.last

internal object Resources {
    fun resourceAsString(fileName: String, delimiter: String = ""): String =
        resourceAsListOfString(fileName).reduce { a, b -> "$a$delimiter$b" }

    fun resourceAsText(fileName: String): String =
        File(fileName.toURI()).readText()

    fun resourceAsListOfString(fileName: String): List<String> =
        File(fileName.toURI()).readLines()

    fun resourceAsListOfInt(fileName: String): List<Int> =
        resourceAsListOfString(fileName).map { it.toInt() }

    fun resourceAsListOfLong(fileName: String): List<Long> =
        resourceAsListOfString(fileName).map { it.toLong() }

    private fun String.toURI(): URI =
        Resources.javaClass.classLoader.getResource(this)?.toURI()
            ?: throw IllegalArgumentException("Cannot find Resource: $this")
}

fun <T> List<T>.nth(n: Int): T =
    this[n % size]

fun String.nth(n: Int): Char =
    this[n % length]

data class Point(
    val x: Int,
    val y: Int
) {
    fun neighbors(grid: List<String>): List<Point> {
        val neighbors = mutableListOf<Point>()
        // north
        if (y - 1 >= 0) neighbors.add(Point(x, y - 1))
        // south
        if (y + 1 < grid.size) neighbors.add(Point(x, y + 1))
        // west
        if (x - 1 >= 0) neighbors.add(Point(x - 1, y))
        // east
        if (x + 1 < grid[0].length) neighbors.add(Point(x + 1, y))

        return neighbors
    }

}

data class LongPoint(
    val x: Long,
    val y: Long
)
