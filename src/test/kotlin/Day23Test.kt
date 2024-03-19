import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day23Test {

    val mapOfHikingTrails = listOf(
        "#.#####################",
        "#.......#########...###",
        "#######.#########.#.###",
        "###.....#.>.>.###.#.###",
        "###v#####.#v#.###.#.###",
        "###.>...#.#.#.....#...#",
        "###v###.#.#.#########.#",
        "###...#.#.#.......#...#",
        "#####.#.#.#######.#.###",
        "#.....#.#.#.......#...#",
        "#.#####.#.#.#########v#",
        "#.#...#...#...###...>.#",
        "#.#.#v#######v###.###v#",
        "#...#.>.#...>.>.#.###.#",
        "#####v#.#.###v#.#.###.#",
        "#.....#...#...#.#.#...#",
        "#.#########.###.#.#.###",
        "#...###...#...#...#.###",
        "###.###.#.###v#####v###",
        "#...#...#.#.>.>.#.>.###",
        "#.###.###.#.###.#.#v###",
        "#.....###...###...#...#",
        "#####################.#",
    )

    @Test
    fun should_load_map_of_hiking_trails() {

        // act
        val mapOfHikingTrails =
            Day23().loadMapOfHikingTrails(Paths.get("src", "test", "resources", "Day23_TestData.txt"))

        // assert
        assertThat(mapOfHikingTrails).hasSize(23)
    }

    @Test
    fun should_find_neighbors_of_slope_up() {
        // act
        val paths = Day23().findAllPaths(mapOfHikingTrails)

        // assert
        assertThat(paths.map { it.path.size }).contains(75, 83, 83, 87, 91, 95)
    }

    @Test
    fun should_render_map_with_path() {
        // arrange
        val path = listOf(
            Point2D(1, 0),
            Point2D(1, 1),
            Point2D(2, 1),
        )

        // act
        val output = Day23().renderMapWithPath(mapOfHikingTrails, path)

        // assert
        assertThat(output).isEqualTo(
            """
            #O#####################
            #OO.....#########...###
            #######.#########.#.###
            ###.....#.>.>.###.#.###
            ###v#####.#v#.###.#.###
            ###.>...#.#.#.....#...#
            ###v###.#.#.#########.#
            ###...#.#.#.......#...#
            #####.#.#.#######.#.###
            #.....#.#.#.......#...#
            #.#####.#.#.#########v#
            #.#...#...#...###...>.#
            #.#.#v#######v###.###v#
            #...#.>.#...>.>.#.###.#
            #####v#.#.###v#.#.###.#
            #.....#...#...#.#.#...#
            #.#########.###.#.#.###
            #...###...#...#...#.###
            ###.###.#.###v#####v###
            #...#...#.#.>.>.#.>.###
            #.###.###.#.###.#.#v###
            #.....###...###...#...#
            #####################.#
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_part_1() {

        // act
        val part1 = Day23().part1(mapOfHikingTrails)

        // assert
        assertThat(part1).isEqualTo(94)
    }
}