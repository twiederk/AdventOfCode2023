import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day06Test {

    @Test
    fun should_load_races() {

        // act
        val races = Day06().loadRaces(Paths.get("src", "test", "resources", "Day06_TestData.txt"))

        // assert
        assertThat(races).containsExactly(
            Race(7, 9),
            Race(15, 40),
            Race(30, 200),
        )
    }

    @Test
    fun should_load_race() {

        // act
        val races = Day06().loadRace(Paths.get("src", "test", "resources", "Day06_TestData.txt"))

        // assert
        assertThat(races).isEqualTo(Race(71530, 940200))
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_0() {

        // act
        val distance = Race(7, 9).holdButton(0)

        // assert
        assertThat(distance).isEqualTo(0)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_1() {

        // act
        val distance = Race(7, 9).holdButton(1)

        // assert
        assertThat(distance).isEqualTo(6)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_2() {

        // act
        val distance = Race(7, 9).holdButton(2)

        // assert
        assertThat(distance).isEqualTo(10)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_3() {

        // act
        val distance = Race(7, 9).holdButton(3)

        // assert
        assertThat(distance).isEqualTo(12)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_4() {

        // act
        val distance = Race(7, 9).holdButton(4)

        // assert
        assertThat(distance).isEqualTo(12)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_5() {

        // act
        val distance = Race(7, 9).holdButton(5)

        // assert
        assertThat(distance).isEqualTo(10)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_6() {
        // arrange

        // act
        val distance = Race(7, 9).holdButton(6)

        // assert
        assertThat(distance).isEqualTo(6)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_7() {

        // act
        val distance = Race(7, 9).holdButton(7)

        // assert
        assertThat(distance).isEqualTo(0)
    }

    @Test
    fun should_return_number_of_ways_to_win_for_race_1() {
        // arrange

        // act
        val wins = Race(7, 9).wins()

        // assert
        assertThat(wins).isEqualTo(4)

    }

    @Test
    fun should_return_number_of_ways_to_win_for_race_2() {
        // arrange

        // act
        val wins = Race(15, 40).wins()

        // assert
        assertThat(wins).isEqualTo(8)
    }

    @Test
    fun should_return_number_of_ways_to_win_for_race_3() {

        // act
        val wins = Race(30, 200).wins()

        // assert
        assertThat(wins).isEqualTo(9)
    }

    @Test
    fun should_return_margin_of_error() {
        // arrange
        val races = listOf(
            Race(7, 9),
            Race(15, 40),
            Race(30, 200),
        )

        // act
        val marginOfError = Day06().calculateMarginOfError(races)

        // assert
        assertThat(marginOfError).isEqualTo(288)

    }
}