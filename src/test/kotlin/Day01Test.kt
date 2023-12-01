import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class Day01Test {

    @Test
    fun loadData() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")

        // act
        val items = Day01().loadData(path)

        // assert
        assertThat(items).hasSize(4)
    }


}