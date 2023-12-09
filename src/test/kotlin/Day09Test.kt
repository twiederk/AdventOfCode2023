import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day09Test {

//    1 -2 -13 -26 -23 28 189 597 1553 3670 8095 16820 33097 61972 110953 190827 316641 508862 794731 1209826 1799849

    @Test
    fun should_load_histories() {
        // arrange

        // act
        val histories = Day09().loadHistories(Paths.get("src", "test", "resources", "Day09_TestData.txt"))

        // assert
        assertThat(histories).hasSize(3)
    }

}