import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day21Test {

    private val smallestMap = listOf(
        "...",
        "S..",
        "..#",
    )

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

    @Test
    fun should_return_true_when_position_is_rock() {
        // arrange

        // act
        val rock = Day21().isRock(mapOfGarden, Point2D(1, 2))

        // assert
        assertThat(rock).isTrue()
    }

    @Test
    fun should_return_false_when_position_is_garden_plot() {
        // arrange

        // act
        val rock = Day21().isRock(mapOfGarden, Point2D(0, 0))

        // assert
        assertThat(rock).isFalse()
    }

    @Test
    fun should_return_false_when_position_is_starting_position() {
        // arrange

        // act
        val rock = Day21().isRock(mapOfGarden, Point2D(5, 5))

        // assert
        assertThat(rock).isFalse()
    }

    @Test
    fun should_render_garden_map_with_final_steps() {
        // act
        val gardenMapWithFinalSteps = Day21().render(smallestMap, listOf(Point2D(0, 0), Point2D(1, 1), Point2D(0, 2)))

        // assert
        assertThat(gardenMapWithFinalSteps).isEqualTo(
            """
            O..
            SO.
            O.#
            """.trimIndent()
        )
    }

    @Test
    fun should_find_final_steps_with_step_1() {

        // act
        val finalSteps = Day21().bfs(smallestMap, Point2D(0, 1), 1)

        // assert
        assertThat(finalSteps).contains(Point2D(0, 0), Point2D(0, 0), Point2D(1, 1), Point2D(0, 2))

    }
}