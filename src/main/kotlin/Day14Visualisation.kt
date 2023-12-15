import utils.KPixelGameEngine
import java.awt.Color

class Day14Visualisation : KPixelGameEngine("AoC in Kotlin 2023 Day 14 - Parabolic Reflector Dish") {

    private val platform = Platform(
        listOf(
            "O....#....".toCharArray(),
            "O.OO#....#".toCharArray(),
            ".....##...".toCharArray(),
            "OO.#O....O".toCharArray(),
            ".O.....O#.".toCharArray(),
            "O.#..O.#.#".toCharArray(),
            "..O..#O..O".toCharArray(),
            ".......O..".toCharArray(),
            "#....###..".toCharArray(),
            "#OO..#....".toCharArray(),
        )
    )

    override fun onCreate() {
        construct(150, 100, 5)
        limitFps = 10
    }

    override fun onUpdate(elapsedTime: Long, frame: Long) {
        for ((y, row) in platform.data.withIndex()) {
            for ((x, char) in row.withIndex()) {
                when (char) {
                    'O' -> draw(x, y, Color.BLUE)
                    '#' -> draw(x, y, Color.BLACK)
                    else -> draw(x, y, Color.LIGHT_GRAY)
                }

            }
        }
    }
}

fun main() {
    Day14Visualisation().start()
}
