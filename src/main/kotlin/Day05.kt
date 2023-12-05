typealias Mapping = Pair<Long, Long>
typealias MappingTable = Map<Long, Long>

class Day05 {

    fun createMappings(rangeInfo: RangeInfo): List<Mapping> {
        val mappings = mutableListOf<Mapping>()
        for (source in rangeInfo.sourceRangeStart..<rangeInfo.sourceRangeStart + rangeInfo.rangeLength) {
            val destination = rangeInfo.destinationRangeStart + (source - rangeInfo.sourceRangeStart)
            mappings.add(Mapping(source, destination))
        }
        return mappings
    }

    fun createMappingTable(rangeInfos: List<RangeInfo>): MappingTable {
        return mapOf()
    }
}

data class RangeInfo(
    val destinationRangeStart: Long,
    val sourceRangeStart: Long,
    val rangeLength: Long
)

