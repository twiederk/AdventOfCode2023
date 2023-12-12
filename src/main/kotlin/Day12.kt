import java.nio.file.Path

class Day12 {

    fun loadConditionRecords(path: Path): List<ConditionRecord> {
        val rawData = Resources.resourceAsListOfString(path.toFile().name)
        return rawData.map {
            ConditionRecord(
                springs = it.substringBefore(" "),
                damagedGroups = it.substringAfter(" ").split(',').map { value -> value.toInt() }
            )
        }
    }

    fun damagedGroup(springs: String): List<Int> {
        var count = 0
        val damagedGroups = mutableListOf<Int>()
        for (char in springs) {
            if (char == '.') {
                if (count > 0) {
                    damagedGroups.add(count)
                }
                count = 0
            } else {
              count++
            }
        }
        if (count > 0)
            damagedGroups.add(count)
        return damagedGroups
    }

}

data class ConditionRecord(
    val springs: String,
    val damagedGroups: List<Int>
)
