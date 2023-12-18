import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day17Test {

    @Test
    fun should_load_heat_map() {

        // act
        val heatMap = Day17().loadHeatMap(Paths.get("src", "test", "resources", "Day17_TestData.txt"))

        // assert
        Assertions.assertThat(heatMap).hasSize(13)

    }
}