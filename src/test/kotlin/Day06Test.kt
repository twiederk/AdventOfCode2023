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
    fun should_return_distance_when_button_is_hold_for_0() {
        // arrange

        // act
        val distance = Race(7, 9).holdButton(0)

        // assert
        assertThat(distance).isEqualTo(0)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_1() {
        // arrange

        // act
        val distance = Race(7, 9).holdButton(1)

        // assert
        assertThat(distance).isEqualTo(6)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_2() {
        // arrange

        // act
        val distance = Race(7, 9).holdButton(2)

        // assert
        assertThat(distance).isEqualTo(10)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_3() {
        // arrange

        // act
        val distance = Race(7, 9).holdButton(3)

        // assert
        assertThat(distance).isEqualTo(12)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_4() {
        // arrange

        // act
        val distance = Race(7, 9).holdButton(4)

        // assert
        assertThat(distance).isEqualTo(12)
    }

    @Test
    fun should_return_distance_when_button_is_hold_for_5() {
        // arrange

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
        // arrange

        // act
        val distance = Race(7, 9).holdButton(7)

        // assert
        assertThat(distance).isEqualTo(0)
    }

}