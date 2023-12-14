import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day13Test {

    private val pattern1Col = Pattern(
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

    private val pattern2Row = Pattern(
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

        // act
        val patterns = Day13().loadPatterns(Paths.get("src", "test", "resources", "Day13_TestData.txt"))

        // assert
        assertThat(patterns).containsExactly(
            pattern1Col,
            pattern2Row
        )
    }



}