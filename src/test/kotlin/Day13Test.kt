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
        // arrange

        // act
        val mirrorRow = pattern2.mirrorRow()

        // assert
        assertThat(mirrorRow).isEqualTo(4)
    }

}