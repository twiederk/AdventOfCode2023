import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day10Test {

    @Test
    fun should_load_maze() {

        // act
        val maze = Day10().loadMaze(Paths.get("src", "test", "resources", "Day10_TestData.txt"))

        // assert
        assertThat(maze).hasSize(5)
    }

}