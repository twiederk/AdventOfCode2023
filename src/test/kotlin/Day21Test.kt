import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
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
    fun should_find_final_steps_when_smallest_map_with_step_1() {

        // act
        val finalSteps = Day21().bfs(smallestMap, Point2D(0, 1), 1)

        // assert
        assertThat(finalSteps).contains(Point2D(0, 0), Point2D(0, 0), Point2D(1, 1), Point2D(0, 2))

    }

    @Test
    fun should_find_final_steps_when_map_of_garden_with_step_1() {

        // act
        val finalSteps = Day21().bfs(mapOfGarden, Point2D(5, 5), 1)

        // assert
        val renderedMap = Day21().render(mapOfGarden, finalSteps)
        assertThat(renderedMap).isEqualTo(
            """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#O#....
            .##.OS####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
            """.trimIndent()
        )
    }

    @Test
    fun should_find_final_steps_when_map_of_garden_with_step_2() {

        // act
        val finalSteps = Day21().bfs(mapOfGarden, Point2D(5, 5), 2)

        // assert
        val renderedMap = Day21().render(mapOfGarden, finalSteps)
        assertThat(renderedMap).isEqualTo(
            """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#O..#..
            ....#.#....
            .##O.O####.
            .##.O#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
            """.trimIndent()
        )
    }

    @Test
    fun should_find_final_steps_when_map_of_garden_with_step_3() {

        // act
        val finalSteps = Day21().bfs(mapOfGarden, Point2D(5, 5), 3)

        // assert
        val renderedMap = Day21().render(mapOfGarden, finalSteps)
        assertThat(renderedMap).isEqualTo(
            """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#.O.#..
            ...O#O#....
            .##.OS####.
            .##O.#...#.
            ....O..##..
            .##.#.####.
            .##..##.##.
            ...........
            """.trimIndent()
        )
    }

    @Test
    fun should_find_final_steps_when_map_of_garden_with_step_6() {

        // act
        val finalSteps = Day21().bfs(mapOfGarden, Point2D(5, 5), 6)

        // assert
        val renderedMap = Day21().render(mapOfGarden, finalSteps)
        assertThat(renderedMap).isEqualTo(
            """
            ...........
            .....###.#.
            .###.##.O#.
            .O#O#O.O#..
            O.O.#.#.O..
            .##O.O####.
            .##.O#O..#.
            .O.O.O.##..
            .##.#.####.
            .##O.##.##.
            ...........
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_part_1_with_map_of_garden() {
        // act
        val result = Day21().part1(mapOfGarden, 6)

        // assert
        assertThat(result).isEqualTo(16)
    }

    @Test
    fun should_find_rock_on_infinite_map_in_positive_x() {

        // act
        val rock = Day21().isRock(mapOfGarden, Point2D(12, 5))

        // assert
        assertThat(rock).isTrue()
    }

    @Test
    fun should_find_rock_on_infinite_map_in_negative_x() {

        // act
        val rock = Day21().isRock(mapOfGarden, Point2D(-2, 5))

        // assert
        assertThat(rock).isTrue()
    }

    @Test
    fun should_find_rock_on_infinite_map_in_positive_y() {

        // act
        val rock = Day21().isRock(mapOfGarden, Point2D(5, 12))

        // assert
        assertThat(rock).isTrue()
    }

    @Test
    fun should_find_rock_on_infinite_map_in_negative_y() {

        // act
        val rock = Day21().isRock(mapOfGarden, Point2D(5, -2))

        // assert
        assertThat(rock).isTrue()
    }

    @Test
    fun should_find_final_steps_when_infinite_map_with_step_6() {

        // act
        val finalSteps = Day21().bfsInfinite(mapOfGarden, Point2D(5, 5), 6)

        // assert
        assertThat(finalSteps.size).isEqualTo(16)
    }

    @Test
    fun should_find_final_steps_when_infinite_map_with_step_10() {

        // act
        val finalSteps = Day21().bfsInfinite(mapOfGarden, Point2D(5, 5), 10)

        // assert
        assertThat(finalSteps.size).isEqualTo(50)
    }

    @Test
    fun should_find_final_steps_when_infinite_map_with_step_5000() {

        // act
        val finalSteps = Day21().bfsInfinite(mapOfGarden, Point2D(5, 5), 5000)

        // assert
        assertThat(finalSteps.size).isEqualTo(16733044)
    }

    @Test
    @Disabled
    fun should_test_many_coords() {
        // arrange

        // act
        for (x in -100..100) {
            for (y in -100..100) {
                val coords = Point2D(x, y)
                try {
                    val isRock = Day21().isRock(mapOfGarden, coords)
                    if (isRock) {
                        println("Rock at $coords")
                    }
                } catch (e: Exception) {
                    println("Exception at $coords")
                }
            }
        }

        // assert

    }
}