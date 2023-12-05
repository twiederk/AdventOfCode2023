import java.nio.file.Path

class Day05 {
    fun loadSeeds(path: Path): List<Long> {
        val firstLine = Resources.resourceAsListOfString(path.toFile().name)[0]
        val seedValues = firstLine.substringAfter("seeds: ").split(" ")
        return seedValues.map { it.toLong() }
    }

    fun loadAlmanac(path: Path): Almanac {
        val data = Resources.resourceAsListOfString(path.toFile().name).drop(2)
        val allCategories = mutableListOf<CategoryTable>()
        var currCategory: MutableList<RangeInfo>? = null
        for (line in data) {
            if (line.isEmpty()) continue
            if (line[0].isDigit()) {
                val (dest, source, range) = line.split(" ").map { it.toLong() }
                currCategory?.add(RangeInfo(dest, source, range))
                continue
            }
            if (currCategory != null) {
                allCategories.add(CategoryTable(currCategory))
            }
            currCategory = mutableListOf()
        }
        currCategory?.let {
            allCategories.add(CategoryTable(currCategory))
        }
        return Almanac(allCategories)
    }

}

data class RangeInfo(
    val destinationRangeStart: Long,
    val sourceRangeStart: Long,
    val rangeLength: Long
) {

    companion object {
        const val NOT_IN_RANGE = -1L
    }

    private val offset: Long = destinationRangeStart - sourceRangeStart

    fun getDestCategory(sourceCategory: Long): Long {
        if (sourceCategory in sourceRangeStart..<sourceRangeStart + rangeLength) {
            return sourceCategory + offset
        }
        return NOT_IN_RANGE
    }
}

data class CategoryTable(
    val rangeInfos: List<RangeInfo>
) {
    fun getDestCategory(sourceCategory: Long): Long {
        return rangeInfos
            .find { it.getDestCategory(sourceCategory) != RangeInfo.NOT_IN_RANGE }?.getDestCategory(sourceCategory)
            ?: return sourceCategory
    }

}

data class Almanac(
    val categoryTables: List<CategoryTable>
) {
    fun getLocation(seed: Long): Long {
        var dest = seed
        for (categoryTable in categoryTables) {
            dest = categoryTable.getDestCategory(dest)
        }
        return dest
    }

    fun getLowestLocation(seeds: List<Long>): Long {
        return seeds.minOf { getLocation(it) }
    }

}