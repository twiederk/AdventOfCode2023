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

    private val expandedGalaxyImage = listOf(
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

    @Test
    fun should_scan_for_galaxies() {
        // arrange

        // act
        val galaxies = Day11().galaxies(expandedGalaxyImage)

        // assert
        galaxies.forEach { println(it) }
        assertThat(galaxies).hasSize(9)
    }

    @Test
    fun should_create_pairs() {
        // arrange
        val galaxies = listOf(
            Point2D(x = 4, y = 0),
            Point2D(x = 9, y = 1),
            Point2D(x = 0, y = 2),
            Point2D(x = 8, y = 5),
            Point2D(x = 1, y = 6),
            Point2D(x = 12, y = 7),
            Point2D(x = 9, y = 10),
            Point2D(x = 0, y = 11),
            Point2D(x = 5, y = 11),
        )

        // act
        val galaxyPairs = Day11().galaxyPairs(galaxies)

        // assert
        assertThat(galaxyPairs).hasSize(36)
    }

    @Test
    fun should_return_manhatten_distance_of_galaxy_1_and_7() {
        // arrange
        val galaxy1 = Point2D(x = 4, y = 0)
        val galaxy7 = Point2D(x = 9, y = 10)

        // act
        val manhattenDistance = galaxy1.manhattenDistance(galaxy7)

        // assert
        assertThat(manhattenDistance).isEqualTo(15)
    }

    @Test
    fun should_sum_distances_of_all_galaxies() {
        // arrange
        val galaxies = listOf(
            Point2D(x = 4, y = 0),
            Point2D(x = 9, y = 1),
            Point2D(x = 0, y = 2),
            Point2D(x = 8, y = 5),
            Point2D(x = 1, y = 6),
            Point2D(x = 12, y = 7),
            Point2D(x = 9, y = 10),
            Point2D(x = 0, y = 11),
            Point2D(x = 5, y = 11),
        )
        val galaxyPairs = Day11().galaxyPairs(galaxies)

        // act
        val sumOfDistances = Day11().sumOfDistances(galaxyPairs)

        // assert
        assertThat(sumOfDistances).isEqualTo(374)

    }
}