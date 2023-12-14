import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day14Test {

    @Test
    fun should_load_platform() {

        // act
        val platform = Day14().loadPlatform(Paths.get("src", "test", "resources", "Day14_TestData.txt"))

        // assert
        assertThat(platform).hasSize(10)
    }
}