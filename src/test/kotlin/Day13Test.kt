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

    @Test
    fun should_check_for_mirror_col_from_left() {
        // arrange

        // act
        val result = pattern1Col.mirrorLeft()

        // assert
        assertThat(result).isEqualTo(Pattern.NO_MIRROR)
    }

    @Test
    fun should_check_for_mirror_col_from_right() {

        // act
        val result = pattern1Col.mirrorRight()

        // assert
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun should_check_for_mirror_row_from_top() {

        // act
        val result = pattern2Row.mirrorTop()

        // assert
        assertThat(result).isEqualTo(Pattern.NO_MIRROR)
    }

    @Test
    fun should_check_for_mirror_row_from_bottom() {

        // act
        val result = pattern2Row.mirrorBottom()

        // assert
        assertThat(result).isEqualTo(3)
    }

}