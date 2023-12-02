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
    fun should_mark_game_4_impossible() {
        // arrange
        val check: GameCheck = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val game = Day02().createGame("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")

        // act
        Day02().checkGame(check, game)

        // assert
        assertThat(game.possible).isFalse
    }

    @Test
    fun should_mark_game_5_possible() {
        // arrange
        val check: GameCheck = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val game = Day02().createGame("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")

        // act
        Day02().checkGame(check, game)

        // assert
        assertThat(game.possible).isTrue
    }

    @Test
    fun should_mark_game_with_exact_match_possible() {
        // arrange
        val check: GameCheck = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val game = Day02().createGame("Game 5: 12 red, 14 blue, 13 green; 14 blue, 12 red, 13 green")

        // act
        Day02().checkGame(check, game)

        // assert
        assertThat(game.possible).isTrue
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

    @Test
    fun should_create_all_games() {
        // arrange
        val loadedGames = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        )

        // act
        val games = Day02().createGames(loadedGames)

        // assert
        assertThat(games).hasSize(5)
    }

    @Test
    fun should_check_all_games() {
        // arrange
        val day02 = Day02()
        val loadedGames = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        )
        val games = day02.createGames(loadedGames)
        val check: GameCheck = mapOf("red" to 12, "green" to 13, "blue" to 14)


        // act
        day02.checkGames(check, games)

        // assert
        assertThat(games[0].possible).isTrue
        assertThat(games[1].possible).isTrue
        assertThat(games[2].possible).isFalse
        assertThat(games[3].possible).isFalse
        assertThat(games[4].possible).isTrue

    }

    @Test
    fun should_sum_up_all_possible_games() {
        // arrange
        val day02 = Day02()
        val check: GameCheck = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val loadedGames = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        )
        val games = day02.createGames(loadedGames)
        day02.checkGames(check, games)

        // act
        val sumOfGames = day02.sumGames(games)

        // assert
        assertThat(sumOfGames).isEqualTo(8)
    }

    @Test
    fun should_return_all_colors_of_Game_1() {
        // arrange
        val game = Day02().createGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

        // act
        Day02().checkGameColors(game)

        // assert
        assertThat(game.allColors).contains("blue", "red", "green", "blue")
    }

    @Test
    fun should_return_all_colors_of_Game_2() {
        // arrange
        val game = Day02().createGame("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")

        // act
        Day02().checkGameColors(game)

        // assert
        assertThat(game.allColors).contains("blue", "green", "red")
    }

    @Test
    fun should_return_power_of_all_colors_max_of_Game1() {
        // arrange
        val game = Day02().createGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

        // act
        val maxColorPower = Day02().powerOfGame(game)

        // assert
        assertThat(maxColorPower).isEqualTo(48)
    }

    @Test
    fun should_return_power_of_all_colors_max_of_Game2() {
        // arrange
        val game = Day02().createGame("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")

        // act
        val maxColorPower = Day02().powerOfGame(game)

        // assert
        assertThat(maxColorPower).isEqualTo(12)
    }

    @Test
    fun should_return_power_of_all_colors_max_of_Game3() {
        // arrange
        val game = Day02().createGame("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")

        // act
        val maxColorPower = Day02().powerOfGame(game)

        // assert
        assertThat(maxColorPower).isEqualTo(1560)
    }

    @Test
    fun should_sum_up_power_of_all_games() {

        // arrange
        val day02 = Day02()
        val loadedGames = listOf(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        )
        val games = day02.createGames(loadedGames)

        // act
        val powerOfGames = day02.powerOfGames(games)

        // assert
        assertThat(powerOfGames).isEqualTo(2286)
    }
}