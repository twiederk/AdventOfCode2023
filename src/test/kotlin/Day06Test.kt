import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day06Test {

    @Test
    fun should_load_races() {
        // arrange

        // act
        val races = Day06().loadRaces(Paths.get("src", "test", "resources", "Day06_TestData.txt"))

        // assert
        assertThat(races).containsExactly(
            Race(7, 9),
            Race(15, 40),
            Race(30, 200),
        )
    }

}