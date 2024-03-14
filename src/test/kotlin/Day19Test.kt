import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day19Test {

    @Test
    fun should_load_workflows() {

        // act
        val workflows = Day19().loadWorkflows(Paths.get("src", "test", "resources", "Day19_TestData.txt"))

        // assert
        assertThat(workflows).hasSize(11)
    }

    @Test
    fun should_load_parts() {
        // act
        val parts = Day19().loadParts(Paths.get("src", "test", "resources", "Day19_TestData.txt"))

        // assert
        assertThat(parts).hasSize(5)

    }
}