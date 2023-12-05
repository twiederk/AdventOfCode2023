import java.nio.file.Path
import java.nio.file.Paths

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

    fun loadSeedRanges(path: Path): List<LongRange> {
        val firstLine =  Resources.resourceAsListOfString(path.toFile().name)[0]
        val seedRangesArray = firstLine.substringAfter("seeds: ").split(" ")
        val seedRanges = mutableListOf<LongRange>()
        for (index in seedRangesArray.indices step 2) {
            val start = seedRangesArray[index].toLong()
            val end = start + seedRangesArray[index + 1].toLong() - 1
            seedRanges.add(start..end)
        }
        return seedRanges
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

    fun getLowestLocationOfSeeds(seeds: List<Long>): Long {
        return seeds.minOf { getLocation(it) }
    }

    fun getLowestLocationOfSeedRanges(seedRanges: List<LongRange>): Long {
        var lowestLocation = Long.MAX_VALUE
        for (seedRange in seedRanges) {
            for (seed in seedRange) {
                val location = getLocation(seed)
                if (location < lowestLocation) {
                    lowestLocation = location
                }
            }
        }
        return lowestLocation
    }

}

fun main() {
    val inputFile = Paths.get("src", "main", "resources", "Day05_InputData.txt")
    val day05 = Day05()

    val seeds = day05.loadSeeds(inputFile)
    val almanac = day05.loadAlmanac(inputFile)
    val lowestLocation = almanac.getLowestLocationOfSeeds(seeds)
    println("Day05 part1: $lowestLocation")

    val seedRanges = day05.loadSeedRanges(inputFile)
    val lowestLocationPart2 = almanac.getLowestLocationOfSeedRanges(seedRanges)
    println("Day05 part2: $lowestLocationPart2")

}