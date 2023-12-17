import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day16Test {

    @Test
    fun should_load_contraption() {

        // act
        val contraption = Day16().loadContraption(Paths.get("src", "test", "resources", "Day16_TestData.txt"))

        // assert
        assertThat(contraption).hasSize(10)
    }

}