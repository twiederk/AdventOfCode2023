import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day14Test {

    private val platform = listOf(
        "O....#....".toCharArray(),
        "O.OO#....#".toCharArray(),
        ".....##...".toCharArray(),
        "OO.#O....O".toCharArray(),
        ".O.....O#.".toCharArray(),
        "O.#..O.#.#".toCharArray(),
        "..O..#O..O".toCharArray(),
        ".......O..".toCharArray(),
        "#....###..".toCharArray(),
        "#OO..#....".toCharArray(),
    )

    @Test
    fun should_load_platform() {

        // act
        val platform = Day14().loadPlatform(Paths.get("src", "test", "resources", "Day14_TestData.txt"))

        // assert
        assertThat(platform).hasSize(10)
    }

    @Test
    fun should_tilt_platform_one_step_to_north() {

        // act
        val nextPlatform = Day14().tilt(platform)

        // assert
        assertThat(nextPlatform).containsExactly(
            "O.OO.#....".toCharArray(),
            "O...#....#".toCharArray(),
            "OO..O##..O".toCharArray(),
            ".O.#...O..".toCharArray(),
            "O....O..#.".toCharArray(),
            "..#...O#.#".toCharArray(),
            "..O..#.O.O".toCharArray(),
            "..........".toCharArray(),
            "#OO..###..".toCharArray(),
            "#....#....".toCharArray(),
        )
    }

    @Test
    fun should_tilt_platform_all_steps_to_north() {

        // act
        val nextPlatform = Day14().tiltAll(platform)

        // assert
        assertThat(nextPlatform).containsExactly(
            "OOOO.#.O..".toCharArray(),
            "OO..#....#".toCharArray(),
            "OO..O##..O".toCharArray(),
            "O..#.OO...".toCharArray(),
            "........#.".toCharArray(),
            "..#....#.#".toCharArray(),
            "..O..#.O.O".toCharArray(),
            "..O.......".toCharArray(),
            "#....###..".toCharArray(),
            "#....#....".toCharArray(),
        )
    }

    @Test
    fun should_calculate_weight_of_platform() {
        // arrange
        val platform = listOf(
            "OOOO.#.O..".toCharArray(),
            "OO..#....#".toCharArray(),
            "OO..O##..O".toCharArray(),
            "O..#.OO...".toCharArray(),
            "........#.".toCharArray(),
            "..#....#.#".toCharArray(),
            "..O..#.O.O".toCharArray(),
            "..O.......".toCharArray(),
            "#....###..".toCharArray(),
            "#....#....".toCharArray(),
        )

        // act
        val weight = Day14().weight(platform)

        // assert
        assertThat(weight).isEqualTo(136)
    }

    @Test
    fun should_solve_part1_with_test_data() {

        // act
        val weight = Day14().part1(platform)

        // assert
        assertThat(weight).isEqualTo(136)
    }

    @Test
    fun should_rotate_platform_90_degrees_to_the_right() {

        // act
        val rotatedPlatform = Day14().rotate(platform)

        // assert
        assertThat(rotatedPlatform).containsExactly(
            "##..O.O.OO".toCharArray(),
            "O....OO...".toCharArray(),
            "O..O#...O.".toCharArray(),
            "......#.O.".toCharArray(),
            "......O.#.".toCharArray(),
            "##.#O..#.#".toCharArray(),
            ".#.O...#..".toCharArray(),
            ".#O.#O....".toCharArray(),
            ".....#....".toCharArray(),
            "...O#.O.#.".toCharArray(),
        )
    }

    @Test
    fun should_run_cycle_1() {

        // act
        val cycledPlatform = Day14().cycle(platform, 1)

        // assert
        assertThat(cycledPlatform).containsExactly(
            ".....#....".toCharArray(),
            "....#...O#".toCharArray(),
            "...OO##...".toCharArray(),
            ".OO#......".toCharArray(),
            ".....OOO#.".toCharArray(),
            ".O#...O#.#".toCharArray(),
            "....O#....".toCharArray(),
            "......OOOO".toCharArray(),
            "#...O###..".toCharArray(),
            "#..OO#....".toCharArray(),
        )
    }

    @Test
    fun should_run_cycle_2() {

        // act
        val cycledPlatform = Day14().cycle(platform, 2)

        // assert
        assertThat(cycledPlatform).containsExactly(
            ".....#....".toCharArray(),
            "....#...O#".toCharArray(),
            ".....##...".toCharArray(),
            "..O#......".toCharArray(),
            ".....OOO#.".toCharArray(),
            ".O#...O#.#".toCharArray(),
            "....O#...O".toCharArray(),
            ".......OOO".toCharArray(),
            "#..OO###..".toCharArray(),
            "#.OOO#...O".toCharArray(),
        )
    }

}