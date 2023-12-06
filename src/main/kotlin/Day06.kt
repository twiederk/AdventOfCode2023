import java.nio.file.Path
import java.nio.file.Paths

class Day06 {

    fun loadRaces(path: Path): List<Race> {
        val rawData = Resources.resourceAsListOfString(path.toFile().name)
        val times = rawData[0].substringAfter("Time:    ").replace("\\s+".toRegex(), "|").split("|").drop(1)
        println("times: $times")
        val distances = rawData[1].substringAfter("Distance:").replace("\\s+".toRegex(), "|").split("|").drop(1)
        println("distances: $distances")
        val races = mutableListOf<Race>()
        for (index in times.indices) {
            races.add(Race(times[index].toInt(), distances[index].toInt()))
        }
        return races
    }

    fun loadRace(path: Path): Race {
        val rawData = Resources.resourceAsListOfString(path.toFile().name)
        val time = rawData[0].substringAfter("Time:    ").replace("\\s+".toRegex(), "").toInt()
        println("time: $time")
        val distance = rawData[1].substringAfter("Distance:").replace("\\s+".toRegex(), "").toInt()
        println("distance: $distance")
        return Race(time, distance)
    }


    fun calculateMarginOfError(races: List<Race>): Int {
        return races.map { it.wins() }.reduce { sum, element -> sum * element }
    }

}

data class Race(
    val time: Int,
    val distance: Int
) {
    fun holdButton(seconds: Int): Int {
        return (time - seconds) * seconds
    }

    fun wins(): Int {
        var wins = 0
        for (seconds in 0..time) {
            if (holdButton(seconds) > distance) {
                wins++
            }
        }
        return wins
    }

}

fun main() {
    val day06 = Day06()
    val races = day06.loadRaces(Paths.get("src", "main", "resources", "Day06_InputData.txt"))
    val part1 = day06.calculateMarginOfError(races)
    println("Day06 part1: $part1")

    val race = day06.loadRace(Paths.get("src", "main", "resources", "Day06_InputData.txt"))
    val part2 = day06.calculateMarginOfError(listOf(race))
    println("Day06 part2: $part2")

}
