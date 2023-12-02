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

    @Test
    fun should_mark_game_1_possible() {
        // arrange
        val check: GameCheck = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val game = Day02().createGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

        // act
        Day02().checkGame(check, game)

        // assert
        assertThat(game.possible).isTrue
    }

    @Test
    fun should_mark_game_2_possible() {
        // arrange
        val check: GameCheck = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val game = Day02().createGame("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")

        // act
        Day02().checkGame(check, game)

        // assert
        assertThat(game.possible).isTrue
    }

    @Test
    fun should_mark_game_3_impossible() {
        // arrange
        val check: GameCheck = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val game = Day02().createGame("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")

        // act
        Day02().checkGame(check, game)

        // assert
        assertThat(game.possible).isFalse
    }

    @Test
    fun should_return_max_of_blue() {
        // arrange
        val game = Day02().createGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

        // act
        val count = game.get("blue")

        // assert
        assertThat(count).isEqualTo(6)
    }

    @Test
    fun should_return_max_of_red() {
        // arrange
        val game = Day02().createGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

        // act
        val count = game.get("red")

        // assert
        assertThat(count).isEqualTo(4)
    }

    @Test
    fun should_return_max_of_green() {
        // arrange
        val game = Day02().createGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

        // act
        val count = game.get("green")

        // assert
        assertThat(count).isEqualTo(2)
    }

}