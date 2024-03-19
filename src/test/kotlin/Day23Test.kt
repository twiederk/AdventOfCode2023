import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day23Test {

    @Test
    fun should_load_map_of_hiking_trails() {

        // act
        val mapOfHikingTrails =
            Day23().loadMapOfHikingTrails(Paths.get("src", "test", "resources", "Day23_TestData.txt"))

        // assert
        assertThat(mapOfHikingTrails).hasSize(23)
    }
}