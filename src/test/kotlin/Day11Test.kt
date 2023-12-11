import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day11Test {

    @Test
    fun should_load_galaxy_image() {

        // act
        val galaxyImage = Day11().loadGalaxyImage(Paths.get("src", "test", "resources", "Day11_TestData.txt"))

        // assert
        assertThat(galaxyImage).hasSize(10)
    }

    @Test
    fun should_return_rows_to_expand() {
        // arrange
        val galaxyImage = listOf(
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#.....",
        )

        // act
        val rowsToExpand = Day11().rowsToExpand(galaxyImage)

        // assert
        assertThat(rowsToExpand).containsExactly(3, 7)

    }
}