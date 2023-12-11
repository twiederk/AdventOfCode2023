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

    @Test
    fun should_find_starting_position_of_simple_maze() {

        // act
        val startingPosition = Day10().startingPosition(simpleMaze)

        // assert
        assertThat(startingPosition).isEqualTo(MazePoint(1, 1, 'S'))

    }

    @Test
    fun should_find_starting_position_of_complex_maze() {

        // act
        val startingPosition = Day10().startingPosition(complexMaze)

        // assert
        assertThat(startingPosition).isEqualTo(MazePoint(0, 2, 'S'))
    }

    @Test
    fun should_return_neighbors_of_north_west_corner() {

        // act
        val neighbors = MazePoint(0, 0).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(0, 1), // south
            MazePoint(1, 0), // east
        )
    }

    @Test
    fun should_return_neighbors_of_north_east_corner() {

        // act
        val neighbors = MazePoint(4, 0).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(4, 1), // south
            MazePoint(3, 0), // west
        )
    }

    @Test
    fun should_return_neighbors_of_south_west_corner() {

        // act
        val neighbors = MazePoint(0, 4).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(0, 3), // north
            MazePoint(1, 4), // east
        )
    }

    @Test
    fun should_return_neighbors_of_south_east_corner() {

        // act
        val neighbors = MazePoint(4, 4).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(4, 3), // north
            MazePoint(3, 4), // west
        )
    }

    @Test
    fun should_return_neighbors_of_center() {

        // act
        val neighbors = MazePoint(1, 1).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(1, 0), // north
            MazePoint(1, 2), // south
            MazePoint(0, 1), // east
            MazePoint(2, 1), // west
        )
    }

    @Test
    fun should_return_neighbors_of_north_wall() {

        // act
        val neighbors = MazePoint(1, 0).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(1, 1), // south
            MazePoint(0, 0), // east
            MazePoint(2, 0), // west
        )
    }

    @Test
    fun should_return_neighbors_of_south_wall() {

        // act
        val neighbors = MazePoint(1, 4).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(1, 3), // north
            MazePoint(0, 4), // east
            MazePoint(2, 4), // west
        )
    }

    @Test
    fun should_return_neighbors_of_east_wall() {

        // act
        val neighbors = MazePoint(0, 1).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(0, 0), // north
            MazePoint(0, 2), // south
            MazePoint(1, 1), // west
        )
    }

    @Test
    fun should_return_neighbors_of_west_wall() {

        // act
        val neighbors = MazePoint(4, 1).cardinalNeighbors(simpleMaze)

        // assert
        assertThat(neighbors).containsExactly(
            MazePoint(4, 0), // north
            MazePoint(4, 2), // south
            MazePoint(3, 1), // east
        )
    }

    @Test
    fun should_return_next_direction() {

        // act
        val direction = (MazePoint(1, 1, '|')).next(MazePoint.NORTH)

        // assert
        assertThat(direction).isEqualTo(MazePoint.NORTH)

    }
}
