class Day02 {

    fun createGame(line: String): Game {
        val score = line.substringAfter("Game ").substringBefore(":").toInt()
        val drawParts = line.substringAfter(":").split(';')
        val draws = mutableListOf<Draw>()
        for (drawPart in drawParts) {
            draws.add(createDraw(drawPart))
        }
        return Game(score, draws)
    }

    fun createDraw(drawPart: String): Draw {
        val cubes = drawPart.split(',')
        val draw = Draw()
        for (cubeLine in cubes) {
            val count = cubeLine.substringAfter(" ").substringBefore(" ").toInt()
            val color = cubeLine.substringAfterLast(" ")
            draw.set(color, count)
        }
        return draw
    }

}

class Draw {

    private val colors: MutableMap<String, Int> = mutableMapOf()

    fun get(color: String): Int {
        return colors.getOrDefault(color, 0)
    }

    fun set(color: String, count: Int) {
        colors[color] = count
    }

}

data class Game(
    val score: Int,
    val draws: List<Draw>
)
