import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day06Test {

    @Test
    fun should_load_data() {
        // arrange

        // act
        val races = Day06().loadData(Paths.get("src", "test", "resources", "Day06_TestData.txt"))

        // assert
        assertThat(races).hasSize(3)
    }

}