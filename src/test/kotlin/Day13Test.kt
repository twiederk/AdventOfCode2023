import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day13Test {

    private val pattern1 = Pattern(
        listOf(
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#.",
        )
    )

    private val pattern2 = Pattern(
        listOf(
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#",
        )
    )

    @Test
    fun should_load_patterns() {
        // arrange

        // act
        val patterns = Day13().loadPatterns(Paths.get("src", "test", "resources", "Day13_TestData.txt"))

        // assert
        assertThat(patterns).hasSize(2)
    }

    @Test
    fun should_find_horizontal_mirror() {

        // act
        val mirrorRow = pattern2.mirrorRow()

        // assert
        assertThat(mirrorRow).isEqualTo(4)
    }

    @Test
    fun should_find_vertical_mirror() {

        // act
        val mirrorRow = pattern1.mirrorCol()

        // assert
        assertThat(mirrorRow).isEqualTo(5)
    }

    @Test
    fun should_return_value_of_pattern2() {

        // act
        val value = pattern2.mirror()

        // assert
        assertThat(value).isEqualTo(400)
    }

    @Test
    fun should_return_value_of_pattern1() {

        // act
        val value = pattern1.mirror()

        // assert
        assertThat(value).isEqualTo(5)
        pattern1.print().forEach { println(it) }
    }

    @Test
    fun should_print_pattern1() {

        // act
        val output = pattern1.print()

        // assert
        assertThat(output).containsExactly(
            "123456789",
            "    ><   ",
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#.",
            "    ><   ",
            "123456789"
        )
    }

    @Test
    fun should_print_pattern2() {
        // act
        val output = pattern2.print()

        // assert
        assertThat(output).containsExactly(
            "1 #...##..# 1",
            "2 #....#..# 2",
            "3 ..##..### 3",
            "4v#####.##.v4",
            "5^#####.##.^5",
            "6 ..##..### 6",
            "7 #....#..# 7"
        )
    }

}