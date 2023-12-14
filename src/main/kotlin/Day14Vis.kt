import utils.KPixelGameEngine
import java.awt.Color

class Day14Vis : KPixelGameEngine("AoC in Kotlin 2023 Day 14 - Parabolic Reflector Dish") {


    override fun onCreate() {
        construct(150, 100, 5)
        limitFps = 10
    }

    override fun onUpdate(elapsedTime: Long, frame: Long) {
        if (frame <= 9L) {
            for (index in 0..100) {
                draw(Pair(index, index), Color.RED)
            }
        } else if (frame <= 30) {
            for (index in 0..100) {
                draw(Pair(index, index), Color.GREEN)
            }
        } else {
            for (index in 0..100) {
                draw(Pair(index, index), Color.BLUE)
            }

        }

    }
}

fun main() {
    Day14Vis().start()
}
