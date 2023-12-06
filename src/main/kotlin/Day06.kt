import java.nio.file.Path

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

}

data class Race(
    val time: Int,
    val distance: Int
)
