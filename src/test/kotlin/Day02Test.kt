import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun should_create_Game() {

        // act
        val game = Day02().createGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

        // assert
        assertThat(game).isNotNull
        assertThat(game.score).isEqualTo(1)
        assertThat(game.draws.size).isEqualTo(3)
    }

    @Test
    fun should_create_Draw() {

        // act
        val draw = Day02().createDraw(" 3 blue, 4 red")

        // assert
        assertThat(draw.get("blue")).isEqualTo(3)
        assertThat(draw.get("red")).isEqualTo(4)
        assertThat(draw.get("yellow")).isEqualTo(0)

    }
}