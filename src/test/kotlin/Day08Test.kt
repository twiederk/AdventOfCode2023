import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day08Test {

    @Test
    fun should_load_instructions() {
        // arrange

        // act
        val instructions = Day08().loadInstructions(Paths.get("src", "test", "resources", "Day08_TestData.txt"))

        // assert
        Assertions.assertThat(instructions).isEqualTo("RL")

    }
}