
class Day05 {

}

data class RangeInfo(
    val destinationRangeStart: Long,
    val sourceRangeStart: Long,
    val rangeLength: Long
) {

    companion object {
        const val NOT_IN_RANGE = -1L
    }

    private val offset : Long = destinationRangeStart - sourceRangeStart

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