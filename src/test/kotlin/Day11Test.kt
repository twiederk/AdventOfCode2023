import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day11Test {

    private val galaxyImage = listOf(
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

    @Test
    fun should_load_galaxy_image() {

        // act
        val galaxyImage = Day11().loadGalaxyImage(Paths.get("src", "test", "resources", "Day11_TestData.txt"))

        // assert
        assertThat(galaxyImage).hasSize(10)
    }

    @Test
    fun should_return_indices_of_rows_to_expand() {

        // act
        val rowsToExpand = Day11().rowsToExpand(galaxyImage)

        // assert
        assertThat(rowsToExpand).containsExactly(3, 7)
    }

    @Test
    fun should_return_indices_of_columns_to_expand() {

        // act
        val colsToExpand = Day11().colsToExpand(galaxyImage)

        // assert
        assertThat(colsToExpand).containsExactly(2, 5, 8)
    }

    @Test
    fun should_expand_cols() {
        // arrange
        val colsToExpand = listOf(2, 5, 8)

        // act
        val expandedCols = Day11().expandCols(galaxyImage, colsToExpand)

        // assert
        assertThat(expandedCols.size).isEqualTo(galaxyImage.size)
        assertThat(expandedCols[0]).isEqualTo("....#........")
    }

    @Test
    fun should_expand_rows() {
        // arrange
        val rowsToExpand = listOf(3, 7)

        // act
        val expandedRows = Day11().expandRows(galaxyImage, rowsToExpand)

        // assert
        assertThat(expandedRows.size).isEqualTo(galaxyImage.size + rowsToExpand.size)
    }


    @Test
    fun should_expand_galaxy_image() {

        // act
        val expandedGalaxyImage = Day11().expandGalaxyImage(galaxyImage)

        // assert
        assertThat(expandedGalaxyImage).containsExactly(
            "....#........",
            ".........#...",
            "#............",
            ".............",
            ".............",
            "........#....",
            ".#...........",
            "............#",
            ".............",
            ".............",
            ".........#...",
            "#....#.......",
        )

    }
}