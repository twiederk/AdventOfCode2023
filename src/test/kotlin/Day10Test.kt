import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day10Test {

    val simpleMaze: Maze = listOf(
        "-L|F7",
        "7S-7|",
        "L|7||",
        "-L-J|",
        "L|-JF",
    )

    val complexMaze: Maze = listOf(
        "7-F7-",
        ".FJ|7",
        "SJLL7",
        "|F--J",
        "LJ.LJ",
    )

    @Test
    fun should_load_maze() {

        // act
        val maze = Day10().loadMaze(Paths.get("src", "test", "resources", "Day10_TestData.txt"))

        // assert
        assertThat(maze).hasSize(5)
    }

    //    01234
//  0 -L|F7
//  1 7S-7|
//  2 L|7||
//  3 -L-J|
//  4 L|-JF
    @Test
    fun should_find_starting_position_of_simple_maze() {

        // act
        val startingPosition = Day10().startingPosition(simpleMaze)

        // assert
        assertThat(startingPosition).isEqualTo(Point(1, 1))

    }

    //   01234
// 0 7-F7-
// 1 .FJ|7
// 2 SJLL7
// 3 |F--J
// 4 LJ.LJ
    @Test
    fun should_find_starting_position_of_complex_maze() {

        // act
        val startingPosition = Day10().startingPosition(complexMaze)

        // assert
        assertThat(startingPosition).isEqualTo(Point(0, 2))

    }
}