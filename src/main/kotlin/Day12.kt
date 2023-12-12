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

}

data class ConditionRecord(
    val springs: String,
    val damagedGroups: List<Int>
)
