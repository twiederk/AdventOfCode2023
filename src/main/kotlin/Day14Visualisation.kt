import utils.KPixelGameEngine
import java.awt.Color
import java.nio.file.Paths

class Day14Visualisation : KPixelGameEngine("AoC in Kotlin 2023 Day 14 - Parabolic Reflector Dish") {

    private var platform = Platform(listOf())

    override fun onCreate() {
        platform = Day14().loadPlatform(Paths.get("src", "main", "resources", "Day14_InputData.txt"))

        construct(platform.data[0].size, platform.data.size, 5)
        limitFps = 10

    }

    override fun onUpdate(elapsedTime: Long, frame: Long) {
        drawPlatform(platform)
        if (frame % 5 == 0L) {
            platform = platform.tiltSingleStep()
        }

    }

    private fun drawPlatform(platform: Platform) {
        for ((y, row) in platform.data.withIndex()) {
            for ((x, char) in row.withIndex()) {
                when (char) {
                    'O' -> draw(x, y, Color.YELLOW)
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
