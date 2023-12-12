import java.nio.file.Path
import java.nio.file.Paths
import kotlin.math.pow

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

    fun springs(springs: String, params: String): String {
        val newSprings = springs.toCharArray()
        var paramsIndex = 0
        for ((index, char) in springs.withIndex()) {
            if (char == '?') {
                newSprings[index] = params[paramsIndex]
                paramsIndex++
            } else {
                newSprings[index] = springs[index]
            }
        }
        return String(newSprings)
    }

    fun params(numberOfQuestionMarks: Int, value: Long): String {
        var binary = decimalToBinary(value)
        while (binary.length < numberOfQuestionMarks)
            binary = ".$binary"
        return binary.replace('1', '#').replace('0', '.')
    }

    private fun decimalToBinary(n: Long): String {
        val intList = mutableListOf<Long>()
        var decimalNumber = n

        while (decimalNumber > 0) {
            intList.add(decimalNumber % 2)
            decimalNumber /= 2
        }
        return intList.reversed().joinToString("")
    }

    fun arrangements(conditionRecord: ConditionRecord): Int {
        val numberOfQuestionMarks = conditionRecord.springs.count { it == '?' }

        val power = 2.0.pow(numberOfQuestionMarks).toLong()
        var arrangements = 0
        for (value in 0..<power) {
            val params = params(numberOfQuestionMarks, value)
            val springs = springs(conditionRecord.springs, params)
            val damagedGroups = damagedGroup(springs)
            if (damagedGroups.size == conditionRecord.damagedGroups.size) {
                var same = true
                for (index in damagedGroups.indices) {
                    if (damagedGroups[index] != conditionRecord.damagedGroups[index]) {
                        same = false
                        break
                    }
                }
                if (same) arrangements++
            }
        }
        return arrangements
    }

    fun part1(conditionRecords: List<ConditionRecord>): Int {
        return conditionRecords.sumOf { arrangements(it) }
    }

}

data class ConditionRecord(
    val springs: String,
    val damagedGroups: List<Int>
)


fun main() {

    val day12 = Day12()
    val conditionRecords = day12.loadConditionRecords(Paths.get("src", "main", "resources", "Day12_InputData.txt"))
    val part1 = day12.part1(conditionRecords)
    println("part1 = $part1")
}