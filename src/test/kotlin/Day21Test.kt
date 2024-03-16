import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day21Test {

    private val mapOfGarden = listOf(
        "...........",
        ".....###.#.",
        ".###.##..#.",
        "..#.#...#..",
        "....#.#....",
        ".##..S####.",
        ".##..#...#.",
        ".......##..",
        ".##.#.####.",
        ".##..##.##.",
        "..........."
    )

    @Test
    fun should_load_map_of_garden() {
        // act
        val mapOfGarden = Day21().loadMapOfGarden(Paths.get("src", "test", "resources", "Day21_TestData.txt"))

        // assert
        assertThat(mapOfGarden).hasSize(11)
    }

    @Test
    fun should_find_starting_position_on_smallest_map() {
        // arrange
        val smallestMap = listOf(
            "...",
            "S..",
            "..#",
        )

        // act
        val startingPosition = Day21().startingPosition(smallestMap)

        // assert
        assertThat(startingPosition).isEqualTo(Point2D(0, 1))
    }

    @Test
    fun should_find_starting_position_on_garden_map() {
        // act
        val startingPosition = Day21().startingPosition(mapOfGarden)

        // assert
        assertThat(startingPosition).isEqualTo(Point2D(5, 5))
    }
}