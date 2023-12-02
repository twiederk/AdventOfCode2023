typealias GameCheck = Map<String, Int>

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

    fun checkGame(check: GameCheck, game: Game) {
        game.possible = true
        for (checkEntry in check.entries) {
            if (checkEntry.value <= game.get(checkEntry.key)) {
                game.possible = false
                break
            }
        }

    }


    fun createGames(loadedGames: List<String>): List<Game> {
        return loadedGames.map { createGame(it) }
    }

    fun sumGames(games: List<Game>): Int {
        return games.filter { it.possible }.sumOf { it.score }
    }

    fun checkGames(gameCheck: GameCheck, games: List<Game>) {
        games.forEach { checkGame(gameCheck, it) }
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
) {
    fun get(color: String): Int {
        return draws.maxOf { it.get(color) }
    }

    var possible = false
}
