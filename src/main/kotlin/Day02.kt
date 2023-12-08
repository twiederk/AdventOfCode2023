import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.name

typealias GameCheck = Map<String, Int>

class Day02 {

    fun loadData(path: Path): List<String> {
        return Resources.resourceAsListOfString(path.name)
    }

    fun createGame(line: String): Game {
        val score = line.substringAfter("Game ").substringBefore(":").toInt()
        val drawParts = line.substringAfter(":").split(';')
        val draws = drawParts.map { createDraw(it) }.toMutableList()
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
            if (checkEntry.value < game.get(checkEntry.key)) {
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

    fun checkGameColors(game: Game) {
        game.allColors = game.draws.flatMap { it.colors.keys }.toSet()
    }

    fun powerOfGame(game: Game): Int {
        checkGameColors(game)
        return game.allColors.map { game.get(it) }.reduce { sum, element -> sum * element }
    }

    fun powerOfGames(games: List<Game>): Int {
        return games.sumOf { powerOfGame(it) }
    }

}

class Draw {

    val colors: MutableMap<String, Int> = mutableMapOf()

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

    var allColors = setOf<String>()
    var possible = false
}

fun main() {
    val day02 = Day02()
    val loadedGames = day02.loadData(Path("src", "main", "resources", "Day02_InputData.txt"))
    val games = day02.createGames(loadedGames)
    val check: GameCheck = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )
    day02.checkGames(check, games)
    val part1 = day02.sumGames(games)
    println("Day02 part1: $part1")

    val part2 = day02.powerOfGames(games)
    println("Day02 part2: $part2")

}